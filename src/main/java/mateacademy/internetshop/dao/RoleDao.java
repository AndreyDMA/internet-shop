package mateacademy.internetshop.dao;

import java.util.Set;

import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;

public interface RoleDao {

    Role setRole(User user, Role.RoleName roleName);

    Role getRoleByName(Role.RoleName roleName);

    Set<Role> getAllRoles(User user);
}

