package ee.mihkel.webshopbackend.util;

import ee.mihkel.webshopbackend.exception.ItemNotFoundException;
import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderUtil {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getDatabaseItems(List<Item> items) throws ItemNotFoundException {
        List<Item> databaseItems = new ArrayList<>();
        for (Item i:items) {
            Optional<Item> foundItem = itemRepository.findById(i.getId());
            if (foundItem.isEmpty()) {
                throw new ItemNotFoundException();
            } else {
                databaseItems.add(foundItem.get());
            }
        }
        return databaseItems;
    }

    public static BigDecimal getSumOfOrder(List<Item> items) {
        return items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
