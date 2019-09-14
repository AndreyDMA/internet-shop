package mateacademy.internetshop;

import mateacademy.internetshop.dao.BucketDao;
import mateacademy.internetshop.dao.ItemDao;
import mateacademy.internetshop.dao.UserDao;
import mateacademy.internetshop.dao.daoimpl.BucketDaoImpl;
import mateacademy.internetshop.dao.daoimpl.ItemDaoImpl;
import mateacademy.internetshop.dao.daoimpl.UserDaoImpl;
import mateacademy.internetshop.db.Storage;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.lib.Injector;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.OrderService;
import mateacademy.internetshop.service.UserService;
import mateacademy.internetshop.service.serviceimpl.BucketServiceImpl;
import mateacademy.internetshop.service.serviceimpl.ItemServiceImpl;
import mateacademy.internetshop.service.serviceimpl.UserServiceImpl;

public class Main {

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Inject
    private static ItemService itemService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    public static void main(String[] args) {
        Item phone = new Item("phone", 200.0);
        Item tv = new Item("TV", 500.0);
        Item pc = new Item("PC", 1000.0);
        itemService.create(phone);
        itemService.create(tv);
        itemService.create(pc);
        System.out.println(Storage.items);

        User firstUser = new User();
        userService.create(firstUser);
        System.out.println(Storage.users);

        Bucket bucket = new Bucket();
        bucketService.create(bucket);
        bucketService.addItem(bucket.getId(), pc.getId());
        System.out.println(Storage.buckets);

        orderService.completeOrder(bucket.getItems(), firstUser.getId());
        System.out.println(Storage.orders);
    }
}
