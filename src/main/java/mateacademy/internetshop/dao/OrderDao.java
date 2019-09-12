package mateacademy.internetshop.dao;

import mateacademy.internetshop.model.Order;

public interface OrderDao {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order delete(Long id);

    Order delete(Order order);
}
