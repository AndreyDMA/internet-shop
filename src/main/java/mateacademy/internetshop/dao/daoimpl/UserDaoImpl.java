package mateacademy.internetshop.dao.daoimpl;

import java.util.NoSuchElementException;

import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        Storage.users.add(user);
        return null;
    }

    @Override
    public User get(Long id) {
        return Storage.users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find user with id " + id));
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getId().equals(user.getId())) {
                Storage.users.set(i, user);
            }
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        Storage.users.removeIf(u -> u.getId().equals(id));
    }
}
