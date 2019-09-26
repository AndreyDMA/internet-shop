package mateacademy.internetshop.dao.daoimpl;

import java.util.List;
import java.util.NoSuchElementException;

import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }

    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Item get(Long itemId) {
        return Storage.items.stream()
                .filter(i -> i.getItemId().equals(itemId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find item with id " + itemId));
    }

    @Override
    public Item update(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getItemId().equals(item.getItemId())) {
                Storage.items.set(i, item);
            }
        }
        return item;
    }

    @Override
    public void delete(Long itemId) {
        Storage.items.removeIf(i -> i.getItemId().equals(itemId));
    }
}
