package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.repository.ItemRepository;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
// veel beaniks tegemise võimalusi:
// @Component
// @Service
// @Repository
@CrossOrigin( origins = "http://localhost:4200" ) // kust saadakse controlleri päringutele ligi
public class ItemController {
    // 1a. PostgreSQL andmebaasiga ühendamine
    // 1b. Swagger
    // 2. Angulariga ühendamine
    // 3. Custom Exceptioneid
    // 4. Cache - vahemälu
    // 5. Docker ja virtualisatsioon

    // otseühenduse selle teise klassiga
    @Autowired
    ItemRepository itemRepository;

    // GET - andmete võtmiseks
    // DELETE - andmete kustutamiseks

    // POST - andmete lisamiseks, kui lisan 1 või mitu uut - nõutakse päringule sisu (Body)
    // PUT - andmete asendamiseks, kui asendan kõik varasemad selle uuega - nõutakse päringule sisu (Body)
    // PATCH - andmete muutmiseks - nõutakse päringule sisu (Body)

    // get päring aadressile localhost:8080/items
    @GetMapping("items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    // delete päring aadressile localhost:8080/items/k
    @DeleteMapping("delete-item/{id}")
        // saab URL-i parameetri kätte, mida üritab teha selliseks tüübiks nagu meil määratud
    public void deleteItem(@PathVariable Long id) {
        //items.remove(id);
        itemRepository.deleteById(id);
    }


    @PostMapping("add-item")
        // nõuab päringuga ka mingisugust sisu (body), mida kui saab, üritab teha Item kujule
    public void addItem(@RequestBody Item item) {
        System.out.println(item);
        itemRepository.save(item); // see on ka editi jaoks, ainult annan juba olemasoleva id kaasa
    }

    @GetMapping("view-item/{id}")
        // Item ehk {title: "asdas",price: 2}
        // Optional - võib olla ka "null"
    public Optional<Item> viewItem(@PathVariable Long id) {
        return itemRepository.findById(id);
    }

    @ApiOperation(value="Edits item from webshop. Needs ID inside body.")
    @PostMapping("edit-item")
    public void editItem(@RequestBody Item item) {
        itemRepository.save(item);
    }
}
