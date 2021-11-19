package ee.mihkel.webshopbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartIdUser {
    private String personalCode;
    private String hashBase64;
    private String verificationCode;
    private String countryCode;
}
