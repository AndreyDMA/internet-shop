package mateacademy.internetshop.dao;

import mateacademy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    User get(Long id);

    User update(User user);

    User delete(Long id);

    User delete(User user);
}
