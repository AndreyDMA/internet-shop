package mateacademy.internetshop.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.OrderService;
import mateacademy.internetshop.service.UserService;

public class CreateOrderController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Bucket bucket = userService.get(userId).getBucket();
        List<Item> orderedItems = bucket.getItems();
        orderService.completeOrder(orderedItems, userService.get(userId));
        bucketService.clear(bucket.getBucketId());
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllOrders");
    }
}
