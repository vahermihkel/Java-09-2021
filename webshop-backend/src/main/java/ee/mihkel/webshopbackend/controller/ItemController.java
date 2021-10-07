package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
// veel beaniks tegemise võimalusi:
// @Component
// @Service
// @Repository
public class ItemController {
    List<String> items = new ArrayList<>(Arrays.asList("Toode1", "Ese1", "Product1", "Item1")) ;

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

    // TODO: peaks Itemiks tegema
    // delete päring aadressile localhost:8080/items/k
    @DeleteMapping("items/{id}")
        // saab URL-i parameetri kätte, mida üritab teha selliseks tüübiks nagu meil määratud
    public void deleteItem(@PathVariable int id) {
        items.remove(id);
    }


    @PostMapping("items")
        // nõuab päringuga ka mingisugust sisu (body), mida kui saab, üritab teha Item kujule
    public void addItem(@RequestBody Item item) {
        itemRepository.save(item); // see on ka editi jaoks, ainult annan juba olemasoleva id kaasa
    }

    // TODO: peaks Item kujule tegema
    @GetMapping("items/{id}")
    public String viewItem(@PathVariable int id) {
        return items.get(id);
    }
    // GET
    // items-view
    // viewItem()
}
