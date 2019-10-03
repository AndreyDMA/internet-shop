package mateacademy.internetshop.dao.daoimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.exceptions.AuthenticationException;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        user.setToken(getToken());
        Storage.users.add(user);
        return user;
    }

    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
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
    public Optional<User> getByToken(String token) {
        return Storage.users.stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> user = Storage.users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("incorrect username or password");
        }
        return user.get();
    }

    /*
    @Override
    public List<Order> getOrders(Long userId) {
        return Storage.orders.stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }*/

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
    public void delete(Long userId) {
        User user = get(userId);
        Storage.users.remove(user);
    }
}
