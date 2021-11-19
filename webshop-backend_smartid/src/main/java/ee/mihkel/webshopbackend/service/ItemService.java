package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.cache.ItemCache;
import ee.mihkel.webshopbackend.exception.ItemNotFoundException;
import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemCache itemCache;

    public List<Item> getAllItems() throws ExecutionException {
        return itemCache.getAllItemsFromCache();
    }

    public Item getItem(Long id) throws ExecutionException {
        return itemCache.getItemFromCache(id);
    }

    public Item getItemFromDatabase(Long id) throws ItemNotFoundException {
        if (itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get();
        }
        throw new ItemNotFoundException();
    }

    public List<Item> getAllItemsFromDatabase() {
        return itemRepository.findAll();
    }

    public void updateItem(Item item) {
        itemRepository.save(item);
        itemCache.updateCache(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
        itemCache.deleteFromCache(id);
    }
}
