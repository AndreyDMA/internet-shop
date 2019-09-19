package mateacademy.internetshop.dao.daoimpl;

import java.util.NoSuchElementException;

import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Order;

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
}
