package mateacademy.internetshop.service.impl;

import java.util.List;

import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.lib.Service;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;
    @Inject
    private static UserDao userDao;

    @Override
    public Order completeOrder(List<Item> items, User user) {
        return orderDao.completeOrder(items, user);
    }

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long orderId) {
        return orderDao.get(orderId);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void deleteUserOrder(User user, Long orderId) {
        orderDao.deleteUserOrder(user, orderId);
    }

    @Override
    public List<Order> getAllOrdersOfUser(User user) {
        return orderDao.getAllOrdersOfUser(user);
    }

    @Override
    public void delete(Long orderId) {
        orderDao.delete(orderId);
    }
}
