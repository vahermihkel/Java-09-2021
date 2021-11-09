package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.input.SmartIdRequestInput;
import ee.mihkel.webshopbackend.model.SmartIdUser;
import ee.sk.mid.MidInputUtil;
import ee.sk.mid.exception.MidInvalidNationalIdentityNumberException;
import ee.sk.smartid.*;
import ee.sk.smartid.rest.dao.Interaction;
import ee.sk.smartid.rest.dao.SemanticsIdentifier;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SmartIdController {

    @PostMapping("smart-id/authenticate")
    public SmartIdUser startAuthentication(@RequestBody SmartIdRequestInput smartIdRequestInput) {
        SmartIdUser smartIdUser = new SmartIdUser();
        try {
            String personCode = MidInputUtil.getValidatedNationalIdentityNumber(smartIdRequestInput.getPersonCode());
            smartIdUser.setPersonCode(personCode);

            System.out.println(smartIdRequestInput);
            if (smartIdRequestInput.getCountryCode() == null) {
                smartIdUser.setCountryCode("EE");
            }

            AuthenticationHash authenticationHash = AuthenticationHash.generateRandomHash();
            smartIdUser.setHashBase64(authenticationHash.getHashInBase64());

            String verificationCode = authenticationHash.calculateVerificationCode();
            smartIdUser.setVerificationCode(verificationCode);

        } catch (MidInvalidNationalIdentityNumberException e) {
            log.error("Isikukood mittevaliidne");
        }
        return smartIdUser;
    }

    @PostMapping("smart-id/authenticate/login")
    public void authenticateAndLogin(@RequestBody SmartIdUser smartIdUser) {
        SmartIdClient client = new SmartIdClient();
        client.setRelyingPartyUUID("00000000-0000-0000-0000-000000000000");
        client.setRelyingPartyName("DEMO");
        client.setHostUrl("https://sid.demo.sk.ee/smart-id-rp/v1/");


        SemanticsIdentifier semanticsIdentifier = new SemanticsIdentifier(
                // 3 character identity type
                // (PAS-passport, IDC-national identity card or PNO - (national) personal number)
                SemanticsIdentifier.IdentityType.PNO,
                SemanticsIdentifier.CountryCode.EE, // 2 character ISO 3166-1 alpha-2 country code
                "30303039914"); // identifier (according to country and identity type reference)

        AuthenticationHash authenticationHash = new AuthenticationHash();
        byte[] hashInBytes = smartIdUser.getHashBase64().getBytes();
        HashType hashType = HashType.SHA512;
        authenticationHash.setHash(DigestCalculator.calculateDigest(hashInBytes,hashType));
        authenticationHash.setHashType(hashType);

        SmartIdAuthenticationResponse authenticationResponse = client
                .createAuthentication()
                .withSemanticsIdentifier(semanticsIdentifier)
                .withAuthenticationHash(authenticationHash)
                .withCertificateLevel("QUALIFIED") // Certificate level can either be "QUALIFIED" or "ADVANCED"
                // Smart-ID app will display verification code to the user and user must insert PIN1
                .withAllowedInteractionsOrder(
                        Collections.singletonList(Interaction.displayTextAndPIN("Log in to self-service?")
                        ))
                .authenticate();

        // You need this later to pull user's signing certificate
        String documentNumberForFurtherReference = authenticationResponse.getDocumentNumber();


        // init Authentication response validator with trusted certificates loaded from within library
// as an alternative you can pass trusted certificates array as parameter to constructor
        AuthenticationResponseValidator authenticationResponseValidator = new AuthenticationResponseValidator();

// throws SmartIdResponseValidationException if validation doesn't pass
        AuthenticationIdentity authIdentity = authenticationResponseValidator.validate(authenticationResponse);

        String givenName = authIdentity.getGivenName(); // e.g. Mari-Liis"
        String surname = authIdentity.getSurname(); // e.g. "Männik"
        String identityCode = authIdentity.getIdentityCode(); // e.g. "47101010033"
        String country = authIdentity.getCountry(); // e.g. "EE", "LV", "LT"

// Date-of-birth is extracted from certificate attribute or parsed from national identity number
// Value is present for all Estonian and Lithuanian persons but not for all Latvian certificates
        Optional<LocalDate> dateOfBirth = authIdentity.getDateOfBirth();

        System.out.println(givenName);
        System.out.println(surname);
        System.out.println(identityCode);
        System.out.println(country);
        System.out.println(dateOfBirth);
    }
}
