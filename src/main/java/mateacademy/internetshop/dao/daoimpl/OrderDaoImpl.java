package mateacademy.internetshop.dao.daoimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Order get(Long orderId) {
        return Storage.orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find order with id " + orderId));
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getOrderId().equals(order.getOrderId())) {
                Storage.orders.set(i, order);
            }
        }
        return order;
    }

    @Override
    public void delete(Long orderId) {
        Order order = get(orderId);
        Storage.orders.removeIf(o -> o.getOrderId().equals(orderId));
    }

    @Override
    public List<Order> getAllOrdersOfUser(User user) {
        return Storage.orders.stream()
                .filter(o -> o.getUser().getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order order = new Order(items, user);
        create(order);
        getAllOrdersOfUser(user).add(order);
        return order;
    }

    @Override
    public void deleteUserOrder(User user, Long orderId) {
        Order order = get(orderId);
        delete(orderId);
        getAllOrdersOfUser(user).remove(order);
    }
}
