package mateacademy.internetshop.dao;

import mateacademy.internetshop.model.Order;

public interface OrderDao {

    Order create(Order order);

    Order get(Long orderId);

    Order update(Order order);

    void delete(Long orderId);
}
