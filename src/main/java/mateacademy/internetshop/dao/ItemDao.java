package mateacademy.internetshop.dao;

import java.util.List;

import mateacademy.internetshop.model.Item;

public interface ItemDao {

    List<Item> getAll();

    Item create(Item item);

    Item get(Long id);

    Item update(Item item);

    void delete(Long id);

    void delete(Item item);
}
