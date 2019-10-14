package mateacademy.internetshop.dao.hibernate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.exceptions.AuthenticationException;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoHibernateImpl implements UserDao {
    Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);

    @Override
    public User create(User user) {
        Long userId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            userId = (Long) session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't create bucket ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        user.setUserId(userId);
        return user;
    }

    @Override
    public User get(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            Hibernate.initialize(user.getOrders());
            return user;
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't update bucket ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public void delete(Long userId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(get(userId));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't delete bucket ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
        }
    }

    @Override
    public byte[] getSaltByLogin(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT salt FROM User WHERE login=:login");
            query.setParameter("login", login);
            byte[] salt = (byte[]) query.uniqueResult();
            return salt;
        }
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE login=:login");
            query.setParameter("login", login);
            User user = (User) query.uniqueResult();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Optional<User> getByToken(String token) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE token=:token");
            query.setParameter("token", token);
            return query.list().stream().findFirst();
        }
    }

    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
    }
}
