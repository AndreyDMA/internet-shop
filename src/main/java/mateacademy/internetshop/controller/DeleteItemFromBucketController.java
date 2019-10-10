package mateacademy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.service.BucketService;
import mateacademy.internetshop.service.UserService;

public class DeleteItemFromBucketController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Long bucketId = userService.get(userId).getBucket().getBucketId();
        Bucket bucket = bucketService.get(bucketId);
        Long itemId = Long.valueOf(req.getParameter("item_id"));
        bucketService.deleteItem(bucketId, itemId);
        resp.sendRedirect(req.getContextPath() + "/servlet/bucket");
    }
}
