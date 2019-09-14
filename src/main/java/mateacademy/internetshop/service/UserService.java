package mateacademy.internetshop.service;

import mateacademy.internetshop.model.User;

public interface UserService {

    User create(User user);
    User get(Long id);
    User update(User user);
    void delete(Long id);
}
