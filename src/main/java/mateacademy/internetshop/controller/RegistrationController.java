package mateacademy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Role;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.UserService;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User();
        newUser.setLogin(req.getParameter("login"));
        newUser.setPassword(req.getParameter("psw"));
        newUser.setName(req.getParameter("user_name"));
        newUser.setSurname(req.getParameter("user_surname"));
        newUser.addRole(Role.of("USER"));
        userService.create(newUser);

        Bucket bucket = new Bucket(newUser.getUserId());
        bucketService.create(bucket);
        Long bucketId = bucket.getBucketId();
        newUser.setBucketId(bucketId);

        HttpSession session = req.getSession(true);
        session.setAttribute("userId", newUser.getUserId());

        Cookie cookie = new Cookie("MATE", newUser.getToken());
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
