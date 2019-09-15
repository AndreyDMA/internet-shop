package mateacademy.internetshop.service;

import mateacademy.internetshop.model.Item;

public interface ItemService {

    Item create(Item item);

    Item get(Long id);

    Item update(Item item);

    void delete(Long id);

    void delete(Item item);
}
