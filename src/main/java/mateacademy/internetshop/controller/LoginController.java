package mateacademy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mateacademy.internetshop.exceptions.AuthenticationException;
import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.service.UserService;
import mateacademy.internetshop.util.HashUtil;

public class LoginController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("psw");
        byte[] salt = userService.getSaltByLogin(login);
        String hashPassword = HashUtil.hashPassword(password, salt);
        try {
            User user = userService.login(login, hashPassword);

            HttpSession session = req.getSession(true);
            session.setAttribute("userId", user.getUserId());

            Cookie cookie = new Cookie("MATE", user.getToken());
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");

        } catch (AuthenticationException e) {
            req.setAttribute("errorMessage", "Incorrect login or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
