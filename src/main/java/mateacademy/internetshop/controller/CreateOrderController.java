package mateacademy.internetshop.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.OrderService;

public class CreateOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> orderedItems = bucketService.getAllItems(0L);
        orderService.completeOrder(orderedItems, 1L);
        bucketService.clear(0L);
        resp.sendRedirect(req.getContextPath() + "/getAllItems");
    }
}
