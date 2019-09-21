package mateacademy.internetshop.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.exceptions.AuthenticationException;
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
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }

    @Override
    public String getToken() {
        return userDao.getToken();
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long userId) {
        return userDao.get(userId);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User delete(Long userId) {
        return userDao.delete(userId);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return userDao.get(userId).getOrders();
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        return userDao.login(login, password);
    }
}
