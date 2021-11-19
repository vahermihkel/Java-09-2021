package ee.mihkel.webshopbackend.model.input;

import lombok.Data;

@Data
public class SmartIdRequestInput {
    private String personCode;
    private String countryCode;
}
