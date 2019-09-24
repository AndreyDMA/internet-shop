package mateacademy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static ItemService itemService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User firstUser = new User("First");
        firstUser.setSurname("User");
        firstUser.setLogin("first");
        firstUser.setPassword("1");
        firstUser.addRole(Role.of("USER"));
        userService.create(firstUser);
        Bucket firstBucket = new Bucket(firstUser.getUserId());
        bucketService.create(firstBucket);
        firstUser.setBucketId(firstBucket.getBucketId());

        User secondUser = new User("Second");
        secondUser.setSurname("User");
        secondUser.setLogin("second");
        secondUser.setPassword("2");
        secondUser.addRole(Role.of("USER"));
        userService.create(secondUser);
        Bucket secondBucket = new Bucket(secondUser.getUserId());
        bucketService.create(secondBucket);
        secondUser.setBucketId(secondUser.getBucketId());

        User admin = new User("Admin");
        admin.setSurname("User");
        admin.setLogin("admin");
        admin.setPassword("111");
        admin.addRole(Role.of("ADMIN"));
        userService.create(admin);

        Item phone = new Item("phone", 200D);
        itemService.create(phone);
        Item tv = new Item("tv", 500D);
        itemService.create(tv);
        Item pc = new Item("pc", 1000D);
        itemService.create(pc);

        resp.sendRedirect(req.getContextPath() + "/index");
    }
}