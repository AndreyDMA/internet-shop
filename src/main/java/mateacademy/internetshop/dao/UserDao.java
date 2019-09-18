package mateacademy.internetshop.dao;

import java.util.List;

import mateacademy.internetshop.model.User;

public interface UserDao {

    User create(User user);

    User get(Long id);

    User update(User user);

    User delete(Long id);

    public List<User> getAll();
}
