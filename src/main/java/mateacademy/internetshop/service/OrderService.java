package mateacademy.internetshop.service;

import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;

import java.util.List;

public interface OrderService {

    Order completeOrder(List<Item> items, Long userId);

    Order create(Order order);
    Order get(Long id);
    Order update(Order order);
    void delete(Long id);
}
