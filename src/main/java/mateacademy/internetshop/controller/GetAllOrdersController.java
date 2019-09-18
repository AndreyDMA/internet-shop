package mateacademy.internetshop.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Order;
import mateacademy.internetshop.service.UserService;

public class GetAllOrdersController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Order> orders = userService.getOrders(1L);
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/WEB-INF/views/allOrders.jsp").forward(req, resp);
    }
}
