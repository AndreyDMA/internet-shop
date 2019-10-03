package mateacademy.internetshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.dao.RoleDao;
import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.dao.jdbc.BucketDaoJdbcImpl;
import mateacademy.internetshop.dao.jdbc.ItemDaoJdbcImpl;
import mateacademy.internetshop.dao.jdbc.OrderDaoJdbcImpl;
import mateacademy.internetshop.dao.jdbc.RoleDaoJdbcImpl;
import mateacademy.internetshop.dao.jdbc.UserDaoJdbcImpl;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.OrderService;
import mateacademy.internetshop.service.RoleService;
import mateacademy.internetshop.service.UserService;
import mateacademy.internetshop.service.serviceimpl.BucketServiceImpl;
import mateacademy.internetshop.service.serviceimpl.ItemServiceImpl;
import mateacademy.internetshop.service.serviceimpl.OrderServiceImpl;
import mateacademy.internetshop.service.serviceimpl.RoleServiceImpl;
import mateacademy.internetshop.service.serviceimpl.UserServiceImpl;
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
            instanceItemDao = new ItemDaoJdbcImpl(connection);
        }
        return instanceItemDao;
    }

    public static BucketDao getBucketDao() {
        if (instanceBucketDao == null) {
            instanceBucketDao = new BucketDaoJdbcImpl(connection);
        }
        return instanceBucketDao;
    }

    public static OrderDao getOrderDao() {
        if (instanceOrderDao == null) {
            instanceOrderDao = new OrderDaoJdbcImpl(connection);
        }
        return instanceOrderDao;
    }

    public static UserDao getUserDao() {
        if (instanceUserDao == null) {
            instanceUserDao = new UserDaoJdbcImpl(connection);
        }
        return instanceUserDao;
    }

    public static RoleDao getRoleDao() {
        if (instanceRoleDao == null) {
            instanceRoleDao = new RoleDaoJdbcImpl(connection);
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
