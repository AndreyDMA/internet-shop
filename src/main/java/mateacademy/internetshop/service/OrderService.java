package mateacademy.internetshop.service;

import java.util.List;

import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;

public interface OrderService {

    Order completeOrder(List<Item> items, User user);

    Order create(Order order);

    Order get(Long orderId);

    Order update(Order order);

    void delete(Long orderId);

    void deleteUserOrder(User user, Long orderId);

    List<Order> getAllOrdersOfUser(User user);
}
