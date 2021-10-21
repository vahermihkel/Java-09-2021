package ee.mihkel.webshopbackend.model.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderSum {
    private BigDecimal totalSum;
}
