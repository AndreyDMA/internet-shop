package mateacademy.internetshop.dao;

import mateacademy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    User get(Long id);

    Long getId(User user);

    User update(User user);

    User delete(Long id);
}
