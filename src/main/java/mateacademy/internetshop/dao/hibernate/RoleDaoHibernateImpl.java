package mateacademy.internetshop.dao.hibernate;

import java.util.Set;

import mateacademy.internetshop.dao.RoleDao;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;

@Dao
public class RoleDaoHibernateImpl implements RoleDao {

    @Override
    public Role setRole(User user, Role.RoleName roleName) {
        return null;
    }

    @Override
    public Role getRoleByName(Role.RoleName roleName) {
        return null;
    }

    @Override
    public Set<Role> getAllRoles(User user) {
        return null;
    }
}
