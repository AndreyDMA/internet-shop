package mateacademy.internetshop.lib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mateacademy.internetshop.Factory;
import mateacademy.internetshop.Main;
import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.dao.OrderDao;
import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.OrderService;
import mateacademy.internetshop.service.UserService;
import mateacademy.internetshop.service.serviceimpl.BucketServiceImpl;
import mateacademy.internetshop.service.serviceimpl.ItemServiceImpl;
import mateacademy.internetshop.service.serviceimpl.OrderServiceImpl;
import mateacademy.internetshop.service.serviceimpl.UserServiceImpl;

public class Injector {

    public static void injectDependency() throws IllegalAccessException {

        Map<Class, Object> getInstances = new HashMap<Class, Object>();
        getInstances.put(ItemService.class, Factory.getItemService());
        getInstances.put(BucketService.class, Factory.getBucketService());
        getInstances.put(OrderService.class, Factory.getOrderService());
        getInstances.put(UserService.class, Factory.getUserService());
        getInstances.put(ItemDao.class, Factory.getItemDao());
        getInstances.put(BucketDao.class, Factory.getBucketDao());
        getInstances.put(OrderDao.class, Factory.getOrderDao());
        getInstances.put(UserDao.class, Factory.getUserDao());

        List<Class> existingClasses = new ArrayList<>();
        existingClasses.add(ItemServiceImpl.class);
        existingClasses.add(BucketServiceImpl.class);
        existingClasses.add(OrderServiceImpl.class);
        existingClasses.add(UserServiceImpl.class);
        existingClasses.add(Main.class);

        for (Class clazz : existingClasses) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getDeclaredAnnotation(Inject.class) != null) {
                    field.setAccessible(true);
                    field.set(null, getInstances.get(field.getType()));
                }
            }
        }
    }
}

