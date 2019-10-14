package mateacademy.internetshop.dao.hibernate;

import java.util.List;

import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.lib.Dao;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    Logger logger = Logger.getLogger(ItemDaoHibernateImpl.class);

    @Override
    public List<Item> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createSQLQuery("SELECT * FROM items").addEntity(Item.class).list();
        }
    }

    @Override
    public Item create(Item item) {
        Long itemId = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            itemId = (Long) session.save(item);
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
        item.setItemId(itemId);
        return item;
    }

    @Override
    public Item get(Long itemId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Item.class, itemId);
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(item);
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
        return item;
    }

    @Override
    public void delete(Long itemId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(get(itemId));
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
}
