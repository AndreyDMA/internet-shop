package mateacademy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.dao.RoleDao;
import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.exceptions.AuthenticationException;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    @Inject
    private static RoleDao roleDao;
    @Inject
    private static BucketDao bucketDao;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, surname, login, password, token, salt) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.setBytes(6, user.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setUserId(resultSet.getLong(1));
            }
            roleDao.setRole(user, Role.RoleName.USER);
            return user;
        } catch (SQLException e) {
            logger.error("Can't create user " + user.getName());
        }
        return null;
    }

    @Override
    public User get(Long userId) {
        String query = "SELECT * FROM users WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                return new User(userId, name, surname);
            }
        } catch (SQLException e) {
            logger.error("Can't get user by id " + userId);
        }
        return null;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, surname = ?, login = ?, "
                + "password = ? WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setLong(5, user.getUserId());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            logger.error("Can't update user " + user.getName());
        }
        return null;
    }

    @Override
    public void delete(Long userId) {
        String query = "DELETE FROM users WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete user by id" + userId);
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users;";
        List<User> usersList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                User user = new User(userId, name, surname);
                usersList.add(user);
            }
            return usersList;
        } catch (SQLException e) {
            logger.error("Can't get list of users ", e);
        }
        return null;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        String getUserQuery = "SELECT * FROM users "
                + "WHERE login = ? AND password = ?;";
        Long userId = null;
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(getUserQuery)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String token = resultSet.getString("token");
                byte[] salt = resultSet.getBytes("salt");
                user.setUserId(userId);
                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setPassword(password);
                user.setToken(token);
                user.setSalt(salt);
                Bucket bucket = bucketDao.get(userId);
                user.setBucketId(bucket.getBucketId());
            } else {
                throw new AuthenticationException("incorrect username or password");
            }
        } catch (SQLException e) {
            logger.error("Can't get user from DB ", e);
        }
        String getRoleQuery = "SELECT roles.role_name "
                + "FROM roles "
                + "INNER JOIN roles_users ON roles_users.role_id = roles.role_id "
                + "WHERE roles_users.user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(getRoleQuery)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String roleName = resultSet.getString("role_name");
                user.addRole(Role.of(roleName));
            }
            return user;
        } catch (SQLException e) {
            logger.error("Can't get role for user with id " + userId);
        }
        return null;
    }

    @Override
    public Optional<User> getByToken(String token) {
        String getUserQuery = "SELECT * FROM users WHERE token = ?;";
        Optional<User> optionalUser = Optional.empty();
        Long userId = null;
        try (PreparedStatement statement = connection.prepareStatement(getUserQuery)) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                userId = resultSet.getLong("user_id");
                user.setToken(token);
                Bucket bucket = bucketDao.get(userId);
                user.setBucketId(bucket.getBucketId());
                optionalUser = Optional.of(user);

            }
        } catch (SQLException e) {
            logger.error("Can't get user from DB ", e);
        }
        String getRoleQuery = "SELECT roles.role_name "
                + "FROM roles "
                + "INNER JOIN roles_users ON roles_users.role_id = roles.role_id "
                + "WHERE roles_users.user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(getRoleQuery)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String roleName = resultSet.getString("role_name");
                optionalUser.get().addRole(Role.of(roleName));
            }
            return optionalUser;
        } catch (SQLException e) {
            logger.error("Can't get user from DB ", e);
        }
        return Optional.empty();
    }

    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public byte[] getSaltByLogin(String login) {
        String query = "SELECT users.salt FROM users WHERE users.login = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBytes("salt");
            }
        } catch (SQLException e) {
            logger.error("Can't get user from DB ", e);
        }
        return null;
    }
}
