package mateacademy.internetshop.dao.hibernate;

import java.util.List;

import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class OrderDaoHibernateImpl implements OrderDao {
    public static Logger logger = Logger.getLogger(OrderDaoHibernateImpl.class);

    @Override
    public Order create(Order order) {
        Long orderId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            orderId = (Long) session.save(order);
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
        order.setOrderId(orderId);
        return order;
    }

    @Override
    public Order get(Long orderId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Order.class, orderId);
        }
    }

    @Override
    public Order update(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(order);
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
        return order;
    }

    @Override
    public void delete(Long orderId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(get(orderId));
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
    public List<Order> getAllOrdersOfUser(User user) {
        return user.getOrders();
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order order = new Order(items, user);
        return create(order);
    }

    @Override
    public void deleteUserOrder(User user, Long orderId) {
        delete(orderId);
    }
}
