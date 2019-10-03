package mateacademy.internetshop.dao;

import java.util.List;

import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;

public interface OrderDao {

    Order create(Order order);

    Order get(Long orderId);

    Order update(Order order);

    void delete(Long orderId);

    List<Order> getAllOrdersOfUser(Long userId);

    Order completeOrder(List<Item> items, Long userId);

    void deleteUserOrder(Long userId, Long orderId);
}
