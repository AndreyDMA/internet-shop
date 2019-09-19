package mateacademy.internetshop.dao;

import java.util.List;

import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    User get(Long userId);

    User update(User user);

    User delete(Long userId);

    public List<User> getAll();

    public List<Order> getOrders(Long userId);
}
