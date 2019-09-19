package mateacademy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Bucket;
import mateacademy.internetshop.service.BucketService;

public class DeleteItemFromBucketController extends HttpServlet {
    private static final Long TEMP_BUCKET_ID = 0L;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long itemId = Long.valueOf(req.getParameter("item_id"));
        Bucket bucket = bucketService.get(TEMP_BUCKET_ID);
        bucketService.deleteItem(bucket.getBucketId(), itemId);
        resp.sendRedirect(req.getContextPath() + "/bucket");
    }
}
