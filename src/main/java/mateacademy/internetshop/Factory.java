package mateacademy.internetshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.dao.RoleDao;
import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.dao.hibernate.BucketDaoHibernateImpl;
import mateacademy.internetshop.dao.hibernate.ItemDaoHibernateImpl;
import mateacademy.internetshop.dao.hibernate.OrderDaoHibernateImpl;
import mateacademy.internetshop.dao.hibernate.RoleDaoHibernateImpl;
import mateacademy.internetshop.dao.hibernate.UserDaoHibernateImpl;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.OrderService;
import mateacademy.internetshop.service.RoleService;
import mateacademy.internetshop.service.UserService;
import mateacademy.internetshop.service.impl.BucketServiceImpl;
import mateacademy.internetshop.service.impl.ItemServiceImpl;
import mateacademy.internetshop.service.impl.OrderServiceImpl;
import mateacademy.internetshop.service.impl.RoleServiceImpl;
import mateacademy.internetshop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class Factory {
    private static ItemDao instanceItemDao;
    private static BucketDao instanceBucketDao;
    private static OrderDao instanceOrderDao;
    private static UserDao instanceUserDao;
    private static RoleDao instanceRoleDao;
    private static Connection connection;
    private static final Logger logger = Logger.getLogger(Factory.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost:3306/test?user=tempUser&password=1234");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Connection to our DB was not provided");
        }
    }

    private Factory() {
    }

    public static ItemDao getItemDao() {
        if (instanceItemDao == null) {
            instanceItemDao = new ItemDaoHibernateImpl();
        }
        return instanceItemDao;
    }

    public static BucketDao getBucketDao() {
        if (instanceBucketDao == null) {
            instanceBucketDao = new BucketDaoHibernateImpl();
        }
        return instanceBucketDao;
    }

    public static OrderDao getOrderDao() {
        if (instanceOrderDao == null) {
            instanceOrderDao = new OrderDaoHibernateImpl();
        }
        return instanceOrderDao;
    }

    public static UserDao getUserDao() {
        if (instanceUserDao == null) {
            instanceUserDao = new UserDaoHibernateImpl();
        }
        return instanceUserDao;
    }

    public static RoleDao getRoleDao() {
        if (instanceRoleDao == null) {
            instanceRoleDao = new RoleDaoHibernateImpl();
        }
        return instanceRoleDao;
    }

    public static ItemService getItemService() {
        return new ItemServiceImpl();
    }

    public static BucketService getBucketService() {
        return new BucketServiceImpl();
    }

    public static OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static RoleService getRoleService() {
        return new RoleServiceImpl();
    }
}
