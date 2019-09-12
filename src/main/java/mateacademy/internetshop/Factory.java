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
    private static ItemDao instanceItem;
    private static ItemService instanceItemService;
    private static BucketDao instanceBucket;
    private static BucketService instanceBucketService;
    private static OrderDao instanceOrder;
    private static OrderService instanceOrderService;
    private static UserDao instanceUser;
    private static UserService instanceUserService;

    private Factory() {
    }

    public static ItemDao getItemDao() {
        if (instanceItem == null) {
            instanceItem = new ItemDaoImpl();
        }
        return instanceItem;
    }

    public static ItemService getItemService() {
        if (instanceItemService == null) {
            instanceItemService = new ItemServiceImpl();
        }
        return new ItemServiceImpl();
    }

    public static BucketDao getBucketDao() {
        if (instanceBucket == null) {
            instanceBucket = new BucketDaoImpl();
        }
        return instanceBucket;
    }

    public static BucketService getBucketService() {
        if (instanceBucketService == null) {
            instanceBucketService = new BucketServiceImpl();
        }
        return new BucketServiceImpl();
    }

    public static OrderDao getOrderDao() {
        if (instanceOrder == null) {
            instanceOrder = new OrderDaoImpl();
        }
        return instanceOrder;
    }

    public static OrderService getOrderService() {
        if (instanceOrderService == null) {
            instanceOrderService = new OrderServiceImpl();
        }
        return new OrderServiceImpl();
    }

    public static UserDao getUserDao() {
        if (instanceUser == null) {
            instanceUser = new UserDaoImpl();
        }
        return instanceUser;
    }

    public static UserService getUserService() {
        if (instanceUserService == null) {
            instanceUserService = new UserServiceImpl();
        }
        return new UserServiceImpl();
    }
}
