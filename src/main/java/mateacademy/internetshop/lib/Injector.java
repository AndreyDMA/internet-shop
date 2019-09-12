package mateacademy.internetshop.lib;

import mateacademy.internetshop.service.serviceimpl.BucketServiceImpl;
import mateacademy.internetshop.service.serviceimpl.ItemServiceImpl;
import mateacademy.internetshop.service.serviceimpl.OrderServiceImpl;
import mateacademy.internetshop.service.serviceimpl.UserServiceImpl;

import java.lang.reflect.Field;

public class Injector {

    public static void injectDependency() throws IllegalAccessException {

        Field[] itemServiceFields = ItemServiceImpl.class.getDeclaredFields();
        Field[] bucketServiceFields = BucketServiceImpl.class.getDeclaredFields();
        Field[] orderServiceFields = OrderServiceImpl.class.getDeclaredFields();
        Field[] userServiceFields = UserServiceImpl.class.getDeclaredFields();

        for (Field field : itemServiceFields) {
            if (field.getAnnotation(Inject.class) != null) {
                field.
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

