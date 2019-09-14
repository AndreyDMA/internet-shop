package mateacademy.internetshop.dao;

import mateacademy.internetshop.model.Item;

public interface ItemDao {

    Item create(Item item);
    Item get(Long id);
    Item update(Item item);
    void delete(Long id);
    void delete(Item item);
}
