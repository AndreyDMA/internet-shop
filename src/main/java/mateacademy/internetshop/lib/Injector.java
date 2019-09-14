package mateacademy.internetshop.lib;

import java.lang.reflect.Field;
import java.util.*;

import mateacademy.internetshop.Factory;
import mateacademy.internetshop.Main;
import mateacademy.internetshop.dao.daoimpl.BucketDaoImpl;
import mateacademy.internetshop.dao.daoimpl.ItemDaoImpl;
import mateacademy.internetshop.dao.daoimpl.OrderDaoImpl;
import mateacademy.internetshop.dao.daoimpl.UserDaoImpl;
import mateacademy.internetshop.service.serviceimpl.BucketServiceImpl;
import mateacademy.internetshop.service.serviceimpl.ItemServiceImpl;
import mateacademy.internetshop.service.serviceimpl.OrderServiceImpl;
import mateacademy.internetshop.service.serviceimpl.UserServiceImpl;

public class Injector {
    private static Map<Class, Object> getInstances = new HashMap<Class, Object>();
    private static List<Class> existingClasses = new ArrayList<>();

    static {
        getInstances.put(ItemServiceImpl.class, Factory.getItemService());
        getInstances.put(BucketServiceImpl.class, Factory.getBucketService());
        getInstances.put(OrderServiceImpl.class, Factory.getOrderService());
        getInstances.put(UserServiceImpl.class, Factory.getUserService());
        getInstances.put(ItemDaoImpl.class, Factory.getItemDao());
        getInstances.put(BucketDaoImpl.class, Factory.getBucketDao());
        getInstances.put(OrderDaoImpl.class, Factory.getOrderDao());
        getInstances.put(UserDaoImpl.class, Factory.getUserDao());

        existingClasses.add(ItemServiceImpl.class);
        existingClasses.add(BucketServiceImpl.class);
        existingClasses.add(OrderServiceImpl.class);
        existingClasses.add(UserServiceImpl.class);
        existingClasses.add(Main.class);
    }

    public static void injectDependency() throws IllegalAccessException {

        for (Class clazz : existingClasses) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getDeclaredAnnotation(Inject.class) != null
                        && getInstances.containsKey(field.getType())
                        && (getInstances.get(field.getType()).getClass().getDeclaredAnnotation(Service.class) != null
                        || getInstances.get(field.getType()).getClass().getDeclaredAnnotation(Dao.class) != null)) {
                    field.setAccessible(true);
                    field.set(null, getInstances.get(field.getType()));
                }
            }
        }
    }
}

