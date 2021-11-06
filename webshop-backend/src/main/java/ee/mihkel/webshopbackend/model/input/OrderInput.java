package ee.mihkel.webshopbackend.model.input;

import ee.mihkel.webshopbackend.model.Item;
import lombok.Data;

import java.util.List;

@Data
public class OrderInput {
    private String personCode;
    private List<Item> items;
}
