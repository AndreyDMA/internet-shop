package mateacademy.internetshop.service;

import java.util.Set;

import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;

public interface RoleService {
    Role setRole(User user, Role.RoleName roleName);

    Role getRoleByName(Role.RoleName roleName);

    Set<Role> getAllRoles(User user);
}
