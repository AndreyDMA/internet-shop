package mateacademy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.UserService;

public class AddItemToBucketController extends HttpServlet {
    private static final Long TEMP_USER_ID = 0L;

    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = userService.get(TEMP_USER_ID).getBucket();
        bucketService.create(bucket);
        Item item = itemService.get(Long.valueOf(req.getParameter("item_id")));
        bucketService.addItem(bucket.getBucketId(), item.getItemId());
        resp.sendRedirect(req.getContextPath() + "/getAllItems");
    }
}
