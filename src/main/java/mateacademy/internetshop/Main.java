package mateacademy.internetshop;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.lib.Injector;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.OrderService;
import mateacademy.internetshop.service.UserService;

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

    }
}
