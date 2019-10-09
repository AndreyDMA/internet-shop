package mateacademy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import mateacademy.internetshop.dao.RoleDao;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;

import org.apache.log4j.Logger;

@Dao
public class RoleDaoJdbcImpl extends AbstractDao<Role> implements RoleDao {
    private static Logger logger = Logger.getLogger(RoleDaoJdbcImpl.class);

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role setRole(User user, Role.RoleName roleName) {
        String roleIdQuery = "SELECT * FROM roles WHERE role_name = ?;";
        Long roleId = null;
        try (PreparedStatement statement = connection.prepareStatement(roleIdQuery)) {
            statement.setString(1, roleName.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                roleId = resultSet.getLong("role_id");
            }
        } catch (SQLException e) {
            logger.error("Can't find role " + roleName);
        }
        String addRoleQuery = "INSERT INTO roles_users (role_id, user_id) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(addRoleQuery)) {
            statement.setLong(1, roleId);
            statement.setLong(2, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't assign role to user " + user.getName());
        }
        return null;
    }

    @Override
    public Role getRoleByName(Role.RoleName roleName) {
        String query = "SELECT * "
                + "FROM roles "
                + "WHERE role_name = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roleName.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long roleId = resultSet.getLong("role_id");
                String roleLabel = resultSet.getString("role_name");
                Role role = Role.of(roleLabel);
                role.setRoleId(roleId);
                return role;
            }
        } catch (SQLException e) {
            logger.error("Can't get role " + roleName.toString());
        }
        return null;
    }

    @Override
    public Set<Role> getAllRoles(User user) {
        String query = "SELECT roles.* "
                + "FROM roles_users "
                + "INNER JOIN roles ON roles_users.role_id = roles.role_id "
                + "WHERE roles_users.user_id = ?;";
        Set<Role> rolesList = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long roleId = resultSet.getLong("role_ id");
                String roleName = resultSet.getString("role_ name");
                Role role = Role.of(roleName);
                role.setRoleId(roleId);
                rolesList.add(role);
                return rolesList;
            }
        } catch (SQLException e) {
            logger.error("Can't get roles from user " + user.getName());
        }
        return null;
    }
}
