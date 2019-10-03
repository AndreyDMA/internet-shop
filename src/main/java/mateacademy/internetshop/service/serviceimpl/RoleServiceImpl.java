package mateacademy.internetshop.service.serviceimpl;

import java.util.Set;

import mateacademy.internetshop.dao.RoleDao;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.lib.Service;
import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Inject
    private static RoleDao roleDao;

    @Override
    public Role setRole(User user, Role.RoleName roleName) {
        return roleDao.setRole(user, roleName);
    }

    @Override
    public Set<Role> getAllRoles(User user) {
        return roleDao.getAllRoles(user);
    }
}
