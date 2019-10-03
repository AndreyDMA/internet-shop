package mateacademy.internetshop.dao;

import java.util.List;
import java.util.Optional;

import mateacademy.internetshop.exceptions.AuthenticationException;
import mateacademy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    User get(Long userId);

    User update(User user);

    void delete(Long userId);

    List<User> getAll();

    //List<Order> getOrders(Long userId);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    String getToken();
}
