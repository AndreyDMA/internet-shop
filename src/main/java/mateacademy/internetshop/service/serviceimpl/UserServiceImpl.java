package mateacademy.internetshop.service.serviceimpl;

import java.util.List;

import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.lib.Service;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public Long getId(User user) {
        return userDao.getId(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return userDao.get(userId).getOrders();
    }
}
