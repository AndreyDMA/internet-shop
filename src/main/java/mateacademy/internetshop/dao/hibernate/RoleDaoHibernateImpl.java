package mateacademy.internetshop.dao.hibernate;

import java.util.Set;

import mateacademy.internetshop.dao.RoleDao;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Dao
public class RoleDaoHibernateImpl implements RoleDao {

    @Override
    public Role setRole(User user, Role.RoleName roleName) {
        Role role = Role.of(roleName.toString());
        user.getRoles().add(role);
        return role;
    }

    @Override
    public Role getRoleByName(Role.RoleName roleName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession())  {
            Query query = session.createQuery("FROM Role WHERE roleName=:name");
            query.setParameter("name", roleName);
            return (Role) query.uniqueResult();
        }
    }

    @Override
    public Set<Role> getAllRoles(User user) {
        return user.getRoles();
    }
}
