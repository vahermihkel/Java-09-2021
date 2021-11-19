package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.Item;

import ee.mihkel.webshopbackend.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("items")
    public List<Item> getItems() throws ExecutionException {
        return itemService.getAllItems();
    }

    @DeleteMapping("delete-item/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }


    @PostMapping("add-item")
    public void addItem(@RequestBody Item item) {
        itemService.updateItem(item);
    }

    @GetMapping("view-item/{id}")
    public Item viewItem(@PathVariable Long id) throws ExecutionException {
        return itemService.getItem(id);
    }

    @ApiOperation(value="Edits item from webshop. Needs ID inside body.")
    @PostMapping("edit-item")
    public void editItem(@RequestBody Item item) {
        itemService.updateItem(item);
    }
}
