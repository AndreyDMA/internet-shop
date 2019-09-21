package mateacademy.internetshop.service.serviceimpl;

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
    public Order completeOrder(List<Item> items, Long userId) {
        Order order = new Order(items, userId);
        orderDao.create(order);
        userDao.get(userId).getOrders().add(order);
        return order;
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
    public void deleteUserOrder(Long userId, Long orderId) {
        Order order = orderDao.get(orderId);
        orderDao.delete(orderId);
        User user = userDao.get(userId);
        user.getOrders().remove(order);
    }

    @Override
    public void delete(Long orderId) {
        orderDao.delete(orderId);
    }
}
