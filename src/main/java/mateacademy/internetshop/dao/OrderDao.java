package mateacademy.internetshop.dao;

import java.util.List;

import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;

public interface OrderDao {

    Order create(Order order);

    Order get(Long orderId);

    Order update(Order order);

    void delete(Long orderId);

    List<Order> getAllOrdersOfUser(User user);

    Order completeOrder(List<Item> items, User user);

    void deleteUserOrder(User user, Long orderId);
}
