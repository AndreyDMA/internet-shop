package mateacademy.internetshop;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.dao.daoimpl.BucketDaoImpl;
import mateacademy.internetshop.dao.daoimpl.ItemDaoImpl;
import mateacademy.internetshop.dao.daoimpl.OrderDaoImpl;
import mateacademy.internetshop.dao.daoimpl.UserDaoImpl;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.OrderService;
import mateacademy.internetshop.service.UserService;
import mateacademy.internetshop.service.serviceimpl.BucketServiceImpl;
import mateacademy.internetshop.service.serviceimpl.ItemServiceImpl;
import mateacademy.internetshop.service.serviceimpl.OrderServiceImpl;
import mateacademy.internetshop.service.serviceimpl.UserServiceImpl;

public class Factory {
    private static ItemDao instanceItemDao;
    private static ItemService instanceItemService;
    private static BucketDao instanceBucketDao;
    private static BucketService instanceBucketService;
    private static OrderDao instanceOrderDao;
    private static OrderService instanceOrderService;
    private static UserDao instanceUserDao;
    private static UserService instanceUserService;

    private Factory() {
    }

    public static ItemDao getItemDao() {
        if (instanceItemDao == null) {
            instanceItemDao = new ItemDaoImpl();
        }
        return instanceItemDao;
    }

    public static ItemService getItemService() {
        if (instanceItemService == null) {
            instanceItemService = new ItemServiceImpl();
        }
        return new ItemServiceImpl();
    }

    public static BucketDao getBucketDao() {
        if (instanceBucketDao == null) {
            instanceBucketDao = new BucketDaoImpl();
        }
        return instanceBucketDao;
    }

    public static BucketService getBucketService() {
        if (instanceBucketService == null) {
            instanceBucketService = new BucketServiceImpl();
        }
        return new BucketServiceImpl();
    }

    public static OrderDao getOrderDao() {
        if (instanceOrderDao == null) {
            instanceOrderDao = new OrderDaoImpl();
        }
        return instanceOrderDao;
    }

    public static OrderService getOrderService() {
        if (instanceOrderService == null) {
            instanceOrderService = new OrderServiceImpl();
        }
        return new OrderServiceImpl();
    }

    public static UserDao getUserDao() {
        if (instanceUserDao == null) {
            instanceUserDao = new UserDaoImpl();
        }
        return instanceUserDao;
    }

    public static UserService getUserService() {
        if (instanceUserService == null) {
            instanceUserService = new UserServiceImpl();
        }
        return new UserServiceImpl();
    }
}
