package mateacademy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.model.User;

public class Storage {

    public static final List<Item> items = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();

    static {
        items.add(new Item());
        items.add(new Item());
        items.add(new Item());

        User firstUser = new User();
        firstUser.setSurname("User");
        firstUser.setLogin("first");
        firstUser.setPassword("1");
        firstUser.setToken(UUID.randomUUID().toString());
        users.add(firstUser);

        User secondUser = new User();
        secondUser.setSurname("User");
        secondUser.setLogin("second");
        secondUser.setPassword("2");
        secondUser.setToken(UUID.randomUUID().toString());
        users.add(secondUser);
    }
}
