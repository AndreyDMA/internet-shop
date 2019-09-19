package mateacademy.internetshop.service;

import java.util.List;

import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;

public interface OrderService {

    Order completeOrder(List<Item> items, Long userId);

    Order create(Order order);

    Order get(Long orderId);

    Order update(Order order);

    void delete(Long orderId);

    void deleteUserOrder(Long userId, Long orderId);
}
