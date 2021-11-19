package ee.mihkel.webshopbackend.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ee.mihkel.webshopbackend.exception.ItemNotFoundException;
import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.repository.ItemRepository;
import ee.mihkel.webshopbackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class ItemCache {

    @Autowired
    ItemService itemService;

    private final LoadingCache<Long, Item> itemCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .build(new CacheLoader<Long, Item>() {
                @Override
                public Item load(Long itemId) throws ItemNotFoundException {
                    return itemService.getItemFromDatabase(itemId);
                }
            });

    public Item getItemFromCache(Long id) throws ExecutionException {
        updateCacheIfEmpty();
        return this.itemCache.get(id);
    }

    public List<Item> getAllItemsFromCache() throws ExecutionException {
        updateCacheIfEmpty();
        return new ArrayList<>(this.itemCache.asMap().values());
    }

    public void updateCache(Item item) {
        updateCacheIfEmpty();
        itemCache.put(item.getId(), item);
    }

    public void deleteFromCache(Long id) {
        itemCache.invalidate(id);
    }

    private void updateCacheIfEmpty() {
        if (itemCache.asMap().values().isEmpty()) {
            itemService.getAllItemsFromDatabase().forEach(item -> itemCache.put(item.getId(), item));
        }
    }
}
