package mateacademy.internetshop.dao.daoimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        Storage.users.add(user);
        return null;
    }

    @Override
    public User get(Long userId) {
        return Storage.users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find user with id " + userId));
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return Storage.orders.stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getUserId().equals(user.getUserId())) {
                Storage.users.set(i, user);
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User delete(Long userId) {
        User user = get(userId);
        Storage.users.remove(user);
        return user;
    }
}
