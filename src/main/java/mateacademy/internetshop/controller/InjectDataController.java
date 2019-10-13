package mateacademy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.dao.RoleDao;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.RoleService;
import mateacademy.internetshop.service.UserService;
import mateacademy.internetshop.util.HashUtil;

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static ItemService itemService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static RoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User firstUser = new User();
        firstUser.setName("First");
        firstUser.setSurname("User");
        firstUser.setLogin("first");
        Role userRole = roleService.getRoleByName(Role.RoleName.valueOf("USER"));
        firstUser.addRole(userRole);
        Bucket firstBucket = new Bucket(firstUser.getUserId());
        firstUser.setBucket(firstBucket);
        bucketService.create(firstBucket);
        firstUser.setToken(userService.getToken());
        firstUser.setSalt(HashUtil.getSalt());
        String firstUserPassword = HashUtil.hashPassword("1", firstUser.getSalt());
        firstUser.setPassword(firstUserPassword);
        userService.create(firstUser);

        User admin = new User();
        admin.setName("Admin");
        admin.setSurname("User");
        admin.setLogin("admin");
        Role adminRole = roleService.getRoleByName(Role.RoleName.valueOf("ADMIN"));
        admin.addRole(adminRole);
        Bucket adminBucket = new Bucket(admin.getUserId());
        admin.setBucket(adminBucket);
        bucketService.create(adminBucket);
        admin.setToken(userService.getToken());
        admin.setSalt(HashUtil.getSalt());
        String adminPassword = HashUtil.hashPassword("111", admin.getSalt());
        admin.setPassword(adminPassword);
        userService.create(admin);

        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
