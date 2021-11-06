package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.Item;

import ee.mihkel.webshopbackend.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


// veel beaniks tegemise võimalusi:
// @Component
// @Service
// @Repository
@RestController
public class ItemController {
    // 1a. PostgreSQL andmebaasiga ühendamine
    // 1b. Swagger
    // 2. Angulariga ühendamine
    // 3. Custom Exceptioneid
    // 4a. Cache - vahemälu
    // 4b. Unit testimine
    // 5. Kasutajaid saab luua ja võtta
    // 6. Docker ja virtualisatsioon
    // 7. SmartId
    // 8. Security
    // 9. JWT
    // 10. Everypay

    // otseühenduse selle teise klassiga
    @Autowired
    ItemService itemService;

    // GET - andmete võtmiseks
    // DELETE - andmete kustutamiseks

    // POST - andmete lisamiseks, kui lisan 1 või mitu uut - nõutakse päringule sisu (Body)
    // PUT - andmete asendamiseks, kui asendan kõik varasemad selle uuega - nõutakse päringule sisu (Body)
    // PATCH - andmete muutmiseks - nõutakse päringule sisu (Body)

    // get päring aadressile localhost:8080/items
    @GetMapping("items")
    public List<Item> getItems() throws ExecutionException {
        return itemService.getAllItems();
    }

    // delete päring aadressile localhost:8080/items/k
    @DeleteMapping("delete-item/{id}")
        // saab URL-i parameetri kätte, mida üritab teha selliseks tüübiks nagu meil määratud
    public void deleteItem(@PathVariable Long id) {
        //items.remove(id);
        itemService.deleteItem(id);
    }


    @PostMapping("add-item")
        // nõuab päringuga ka mingisugust sisu (body), mida kui saab, üritab teha Item kujule
    public void addItem(@RequestBody Item item) {
        System.out.println(item);
        itemService.updateItem(item);
    }

    @GetMapping("view-item/{id}")
        // Item ehk {title: "asdas",price: 2}
        // Optional - võib olla ka "null"
    public Item viewItem(@PathVariable Long id) throws ExecutionException {
        return itemService.getItem(id);
    }

    @ApiOperation(value="Edits item from webshop. Needs ID inside body.")
    @PostMapping("edit-item")
    public void editItem(@RequestBody Item item) {
        itemService.updateItem(item);
    }
}
