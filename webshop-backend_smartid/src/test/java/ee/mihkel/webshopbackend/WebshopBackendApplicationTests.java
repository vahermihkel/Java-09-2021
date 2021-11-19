package ee.mihkel.webshopbackend;

import ee.mihkel.webshopbackend.model.Category;
import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class WebshopBackendApplicationTests {

    @Autowired
    ItemService itemService;

    @Test
    void contextLoads() {
    }

//    @Test
//    void assertThatAddedItemExistsInRepository_ifAdded() throws ExecutionException {
//        Category category = new Category();
//        category.setName("Kat1");
//        itemService.updateItem(new Item(99L,"Ese1",BigDecimal.TEN,"www.ee",category, true));
//        Item item = itemService.getItem(99L);
//        Assertions.assertEquals("Ese1", item.getTitle());
//    }
//
//    @Test
//    void assertThatAddedItemIsNotInRepository_ifDeleted() {
//        EmptyResultDataAccessException ex = Assertions.assertThrows(EmptyResultDataAccessException.class,
//                this::deleteItemFromService,
//                "Expected deleteItemFromService to throw, but it didn't"
//        );
//
//        Assertions.assertEquals("No class ee.mihkel.webshopbackend.model.Item entity with id 99 exists!", ex.getMessage());
//    }
//
//    private void deleteItemFromService() {
//        itemService.updateItem(new Item(99L,"Ese1",BigDecimal.TEN,"www.ee","kategooria", true));
//        itemService.deleteItem(99L);
//    }
}
