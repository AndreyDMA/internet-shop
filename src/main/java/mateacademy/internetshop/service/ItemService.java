package mateacademy.internetshop.service;

import java.util.List;

import mateacademy.internetshop.model.Item;

public interface ItemService {

    List<Item> getAll();

    Item create(Item item);

    Item get(Long itemId);

    Item update(Item item);

    void delete(Long itemId);

    void delete(Item item);
}
