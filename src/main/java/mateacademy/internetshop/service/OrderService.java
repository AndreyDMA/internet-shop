package mateacademy.internetshop.service;

import mateacademy.internetshop.model.Order;

public interface OrderService {

    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order delete(Long id);

    Order delete(Order order);
}
