package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.SmartIdUser;
import ee.mihkel.webshopbackend.model.SmartUserRequest;
import ee.sk.mid.MidInputUtil;
import ee.sk.mid.exception.MidInvalidNationalIdentityNumberException;
import ee.sk.smartid.*;
import ee.sk.smartid.exception.*;
import ee.sk.smartid.rest.dao.NationalIdentity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class SmartIdController {

    @PostMapping("/authenticate/smart-id/")
    public SmartIdUser startSmartIdAuthentication(@RequestBody SmartUserRequest userRequest) {
        SmartIdUser smartIdUser = new SmartIdUser();
        String personalCode;

        if (userRequest.getCountryCode().isEmpty()) {
            userRequest.setCountryCode("EE");
        }

        try {
            personalCode = MidInputUtil.getValidatedNationalIdentityNumber(userRequest.getNationalIdentityNumber());
        }
        catch (MidInvalidNationalIdentityNumberException e) {
            throw new MidInvalidNationalIdentityNumberException ("MidInvalidNationalIdentityNumberException");
        }

        AuthenticationHash authenticationHash = AuthenticationHash.generateRandomHash();
        String verificationCode = authenticationHash.calculateVerificationCode();

        smartIdUser.setHashBase64(authenticationHash.getHashInBase64());
        smartIdUser.setPersonalCode(personalCode);
        smartIdUser.setVerificationCode(verificationCode);
        smartIdUser.setCountryCode(userRequest.getCountryCode());

        return smartIdUser;
    }

    @ApiOperation("Smart Id")
    @PostMapping("/authenticate/smart-id/login/")
    public SmartIdAuthenticationResult authenticateSmartIdAndLogin(@RequestBody SmartIdUser smartIdUser) {
        NationalIdentity nationalIdentity = new NationalIdentity();
        nationalIdentity.setCountryCode("EE");
        nationalIdentity.setNationalIdentityNumber(smartIdUser.getPersonalCode());

        String hashBase64 = smartIdUser.getHashBase64();
        HashType hashType = HashType.SHA512;
        byte[] hashInBytes = hashBase64.getBytes();

        AuthenticationHash authenticationHash = new AuthenticationHash();
        byte[] generatedDigest = DigestCalculator.calculateDigest(hashInBytes, hashType);
        authenticationHash.setHash(generatedDigest);
        authenticationHash.setHashType(hashType);

        SmartIdClient smartIdClient= new SmartIdClient();
        smartIdClient.setRelyingPartyUUID("00000000-0000-0000-0000-000000000000");
        smartIdClient.setRelyingPartyName("DEMO");
        smartIdClient.setHostUrl("https://sid.demo.sk.ee/smart-id-rp/v1/");

        smartIdClient.setTrustedSSLCertificates("-----BEGIN CERTIFICATE-----\n" +
                "MIIGoTCCBYmgAwIBAgIQDnVGUt8ByTTAL8I6G125mTANBgkqhkiG9w0BAQsFADBP\n" +
                "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMSkwJwYDVQQDEyBE\n" +
                "aWdpQ2VydCBUTFMgUlNBIFNIQTI1NiAyMDIwIENBMTAeFw0yMTA5MTQwMDAwMDBa\n" +
                "Fw0yMjEwMTUyMzU5NTlaMFUxCzAJBgNVBAYTAkVFMRAwDgYDVQQHEwdUYWxsaW5u\n" +
                "MRswGQYDVQQKExJTSyBJRCBTb2x1dGlvbnMgQVMxFzAVBgNVBAMTDnNpZC5kZW1v\n" +
                "LnNrLmVlMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoPlayi/tYF3d\n" +
                "XZpcjPGHSR2SyzoEyN/NI76o/lx8zxD+OAh8L1VhagY6nQu1v+VDYMWYDY5ecOpE\n" +
                "rjQa1SyOCnqowrhXGHTGs/7lsZhk2m0cI4mVdyueKHfee5Z/wHhzfah4VogcXLir\n" +
                "uolLvDF9ox5Fu1XwqPfCVpdauorVTbzm/3Bi/mRGcQ2c2nupwmv7jTWKnBGde41c\n" +
                "21GtpYKgNEPYrhH8xeLTpnzDIVPk8x5cfXBqdTIYynGpdbkiDkIvWQr9S0yx6Yur\n" +
                "WZy7vTGy4kfyC9u6h90LIMY6LwRm2WlIxbXxwMwcpz5I/2zmmcU7h9WKfrHDa/w/\n" +
                "LZXDh916qwIDAQABo4IDcTCCA20wHwYDVR0jBBgwFoAUt2ui6qiqhIx56rTaD5iy\n" +
                "xZV2ufQwHQYDVR0OBBYEFNwlDB9dSMdPoacISAQLAg6Ev33yMBkGA1UdEQQSMBCC\n" +
                "DnNpZC5kZW1vLnNrLmVlMA4GA1UdDwEB/wQEAwIFoDAdBgNVHSUEFjAUBggrBgEF\n" +
                "BQcDAQYIKwYBBQUHAwIwgY8GA1UdHwSBhzCBhDBAoD6gPIY6aHR0cDovL2NybDMu\n" +
                "ZGlnaWNlcnQuY29tL0RpZ2lDZXJ0VExTUlNBU0hBMjU2MjAyMENBMS0zLmNybDBA\n" +
                "oD6gPIY6aHR0cDovL2NybDQuZGlnaWNlcnQuY29tL0RpZ2lDZXJ0VExTUlNBU0hB\n" +
                "MjU2MjAyMENBMS0zLmNybDA+BgNVHSAENzA1MDMGBmeBDAECAjApMCcGCCsGAQUF\n" +
                "BwIBFhtodHRwOi8vd3d3LmRpZ2ljZXJ0LmNvbS9DUFMwfwYIKwYBBQUHAQEEczBx\n" +
                "MCQGCCsGAQUFBzABhhhodHRwOi8vb2NzcC5kaWdpY2VydC5jb20wSQYIKwYBBQUH\n" +
                "MAKGPWh0dHA6Ly9jYWNlcnRzLmRpZ2ljZXJ0LmNvbS9EaWdpQ2VydFRMU1JTQVNI\n" +
                "QTI1NjIwMjBDQTEtMS5jcnQwDAYDVR0TAQH/BAIwADCCAX4GCisGAQQB1nkCBAIE\n" +
                "ggFuBIIBagFoAHYAKXm+8J45OSHwVnOfY6V35b5XfZxgCvj5TV0mXCVdx4QAAAF7\n" +
                "5Fy6EAAABAMARzBFAiBzPohl9WhlXYo3ZnzyvAt8yrskSGDQfUA85MaJYqROFQIh\n" +
                "ANPU3gTrMnb53zXB88/E02H0gwI7EwGZvHOHpHSZeogFAHYAUaOw9f0BeZxWbbg3\n" +
                "eI8MpHrMGyfL956IQpoN/tSLBeUAAAF75Fy6ZQAABAMARzBFAiAGNffVok6aby54\n" +
                "HyhXq1wpWs7aqiXfLam8tEgv1+LMsgIhAJVlmFJvFpGDrb/slzpfkXsnmxR+2x7R\n" +
                "ZxJ9g/BL11I8AHYAQcjKsd8iRkoQxqE6CUKHXk4xixsD6+tLx2jwkGKWBvYAAAF7\n" +
                "5Fy6cQAABAMARzBFAiEArrilG+ktKQmkqFNt/xc1qT8PRSuK8GP/Rw1k1JV80ZwC\n" +
                "IFh+87prTItzwj58SmCHxMCdYPqXfvncNxNoU4eDvf8UMA0GCSqGSIb3DQEBCwUA\n" +
                "A4IBAQAQFnbPOfZzbciz5SUMitzR5xeHEzElm/F3L5jX9wkWSOXC1QjONJnud0UW\n" +
                "QbV72/5YYHOm0c/ghgk9kQbACHchnPSh4YdDVF6sdJclwfreOOzm3r6fJRSQthzf\n" +
                "csX335hzSU60wwcrU+XUI5DlGVdGWFU3rF4FOslzqztEuKviQQ1VdwErNaBqgo67\n" +
                "FuXTc4b3CGp81gwBizWfTyY4aoxj/EQ24ZixLh0KdbRqhsCJOTnkSDM8i74d00le\n" +
                "xRdVRCbU8ebsADUTrrChmeRVXnJOazoiZcJaIHfWanIQmpsGMa0jBGYloOZT/uso\n" +
                "d3vpLl9EudSMsOdFPKaKfdSH+Qq/\n" +
                "-----END CERTIFICATE-----");


        SmartIdAuthenticationResult authenticationResult;

        try {
            SmartIdAuthenticationResponse authenticationResponse = smartIdClient
                    .createAuthentication()
                    .withNationalIdentity(nationalIdentity)
                    .withAuthenticationHash(authenticationHash)
                    .withDisplayText("Log into webshop")
                    .withCertificateLevel("QUALIFIED")
                    .authenticate();
            AuthenticationResponseValidator validator = new AuthenticationResponseValidator();
            authenticationResult = validator.validate(authenticationResponse);
        } catch (UserRefusedException e) {
            throw new SmartIdException("UserRefusedException");
        } catch (SessionTimeoutException e) {
            throw new SmartIdException("SessionTimeoutException");
        } catch (TechnicalErrorException e) {
            throw new SmartIdException("TechnicalErrorException");
        } catch (InvalidParametersException e) {
            throw new SmartIdException("InvalidParametersException");
        } catch (UserAccountNotFoundException e) {
            throw new SmartIdException("UserAccountNotFoundException");
        } catch (RequestForbiddenException e) {
            throw new SmartIdException("RequestForbiddenException");
        } catch (ClientNotSupportedException e) {
            throw new SmartIdException("ClientNotSupportedException");
        } catch (ServerMaintenanceException e) {
            throw new SmartIdException("ServerMaintenanceException");
        }

        log.info(authenticationResult.getAuthenticationIdentity().getSurName());
        log.info(authenticationResult.getAuthenticationIdentity().getGivenName());
        log.info(authenticationResult.getAuthenticationIdentity().getIdentityCode());
        log.info(authenticationResult.getAuthenticationIdentity().getCountry());

        return authenticationResult;
    }
}
