package ee.mihkel.webshopbackend.model;

import lombok.Data;

@Data
public class SmartIdUser {
    private String personCode;
    private String countryCode;
    private String hashBase64;
    private String verificationCode;
}
