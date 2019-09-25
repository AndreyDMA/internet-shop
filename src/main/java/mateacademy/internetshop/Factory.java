package mateacademy.internetshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.dao.daoimpl.BucketDaoImpl;
import mateacademy.internetshop.dao.daoimpl.OrderDaoImpl;
import mateacademy.internetshop.dao.daoimpl.UserDaoImpl;
import mateacademy.internetshop.dao.jdbc.ItemDaoJdbcImpl;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.OrderService;
import mateacademy.internetshop.service.UserService;
import mateacademy.internetshop.service.serviceimpl.BucketServiceImpl;
import mateacademy.internetshop.service.serviceimpl.ItemServiceImpl;
import mateacademy.internetshop.service.serviceimpl.OrderServiceImpl;
import mateacademy.internetshop.service.serviceimpl.UserServiceImpl;
import org.apache.log4j.Logger;

public class Factory {
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
        return new ItemDaoJdbcImpl(connection);
    }

    public static ItemService getItemService() {
        return new ItemServiceImpl();
    }

    public static BucketDao getBucketDao() {
        return new BucketDaoImpl();
    }

    public static BucketService getBucketService() {
        return new BucketServiceImpl();
    }

    public static OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }

    public static OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static UserService getUserService() {
        return new UserServiceImpl();
    }
}
