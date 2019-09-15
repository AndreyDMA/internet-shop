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
    public Order get(Long id) {
        return Storage.orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find order with id " + id));
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getId().equals(order.getId())) {
                Storage.orders.set(i, order);
            }
        }
        return order;
    }

    @Override
    public void delete(Long id) {
        Storage.orders.removeIf(o -> o.getId().equals(id));
    }
}
