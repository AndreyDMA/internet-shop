package mateacademy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mateacademy.internetshop.exceptions.AuthenticationException;
import mateacademy.internetshop.model.User;

public interface UserService {

    User create(User user);

    User get(Long userId);

    User update(User user);

    void delete(Long userId);

    List<User> getAll();

    byte[] getSaltByLogin(String login);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    String getToken();
}
