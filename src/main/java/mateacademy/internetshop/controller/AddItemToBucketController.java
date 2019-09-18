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

public class AddItemToBucketController extends HttpServlet {
    private Bucket bucket = new Bucket();

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        bucketService.create(bucket);
        Item item = itemService.get(Long.valueOf(req.getParameter("item_id")));
        bucketService.addItem(bucket.getId(), item.getId());

        resp.sendRedirect(req.getContextPath() + "/getAllItems");

    }
}
