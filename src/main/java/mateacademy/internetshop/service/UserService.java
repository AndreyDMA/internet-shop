package mateacademy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mateacademy.internetshop.exceptions.AuthenticationException;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;

public interface UserService {

    User create(User user);

    User get(Long userId);

    User update(User user);

    User delete(Long userId);

    List<User> getAll();

    List<Order> getOrders(Long userId);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    String getToken();
}
