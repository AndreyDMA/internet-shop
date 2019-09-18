package mateacademy.internetshop.service;

import java.util.List;

import mateacademy.internetshop.model.User;

public interface UserService {

    User create(User user);

    User get(Long id);

    User update(User user);

    User delete(Long id);

    List<User> getAll();

    List getOrders(Long userId);
}
