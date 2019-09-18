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
    public Item get(Long id) {
        return Storage.items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find item with id " + id));
    }

    @Override
    public Item update(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getId().equals(item.getId())) {
                Storage.items.set(i, item);
            }
        }
        return item;
    }

    @Override
    public void delete(Long id) {
        Storage.items.removeIf(i -> i.getId().equals(id));
    }

    @Override
    public void delete(Item item) {
        Storage.items.removeIf(i -> i.equals(item));
    }

}
