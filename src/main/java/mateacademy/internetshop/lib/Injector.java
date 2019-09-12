package mateacademy.internetshop.lib;

import java.lang.reflect.Field;

import mateacademy.internetshop.Factory;
import mateacademy.internetshop.service.serviceimpl.BucketServiceImpl;
import mateacademy.internetshop.service.serviceimpl.ItemServiceImpl;
import mateacademy.internetshop.service.serviceimpl.OrderServiceImpl;
import mateacademy.internetshop.service.serviceimpl.UserServiceImpl;

public class Injector {

    public static void injectDependency() throws IllegalAccessException {

        Field[] itemServiceFields = ItemServiceImpl.class.getDeclaredFields();
        for (Field field : itemServiceFields) {
            if (field.getAnnotation(Inject.class) != null) {
                if (ItemServiceImpl.class.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getItemDao());
                }
            }
        }

        Field[] bucketServiceFields = BucketServiceImpl.class.getDeclaredFields();
        for (Field field : bucketServiceFields) {
            if (field.getAnnotation(Inject.class) != null) {
                if (BucketServiceImpl.class.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getBucketDao());
                }
            }
        }

        Field[] orderServiceFields = OrderServiceImpl.class.getDeclaredFields();
        for (Field field : orderServiceFields) {
            if (field.getAnnotation(Inject.class) != null) {
                if (OrderServiceImpl.class.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getOrderDao());
                }
            }
        }

        Field[] userServiceFields = UserServiceImpl.class.getDeclaredFields();
        for (Field field : userServiceFields) {
            if (field.getAnnotation(Inject.class) != null) {
                if (UserServiceImpl.class.getDeclaredAnnotation(Dao.class) != null) {
                    field.setAccessible(true);
                    field.set(null, Factory.getUserDao());
                }
            }
        }
    }
}

//        Class<ConsoleHandler> consoleHandlerClass = ConsoleHandler.class;
//        Class<UserEmailDaoImpl> userEmailDaoClass = UserEmailDaoImpl.class;
//        Class<UserDaoImpl> userDaoClass = UserDaoImpl.class;
//
//        Field[] consoleHandlerFields = consoleHandlerClass.getDeclaredFields();
//        for (Field field : consoleHandlerFields) {
//            if (field.getDeclaredAnnotation(Inject.class) != null) {
//                if (field.getType() == UserEmailDao.class
//                        && UserEmailDaoImpl.class.getDeclaredAnnotation(Dao.class) != null) {
//                    field.setAccessible(true);
//                    field.set(null, UserEmailDaoFactory.getUserEmailDao());
//                }
//                if (field.getType() == UserDao.class
//                        && UserDaoImpl.class.getDeclaredAnnotation(Dao.class) != null) {
//                    field.setAccessible(true);
//                    field.set(null, UserDaoFactory.getUserDao());
//                }
//            }
//        }
//    }

