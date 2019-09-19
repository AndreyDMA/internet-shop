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

public class CreateOrderController extends HttpServlet {
    private static final Long TEMP_BUCKET_ID = 0L;
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = bucketService.get(TEMP_BUCKET_ID);
        List<Item> orderedItems = bucketService.getAllItems(bucket.getBucketId());
        orderService.completeOrder(orderedItems, bucket.getUserId());
        bucketService.clear(bucket.getBucketId());
        resp.sendRedirect(req.getContextPath() + "/getAllOrders");
    }
}
