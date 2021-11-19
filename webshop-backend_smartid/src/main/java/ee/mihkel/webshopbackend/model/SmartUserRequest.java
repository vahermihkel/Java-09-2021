package ee.mihkel.webshopbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartUserRequest {
    private String countryCode;
    private String nationalIdentityNumber;
}
