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
import mateacademy.internetshop.service.ItemService;
import mateacademy.internetshop.service.UserService;

public class GetAllItemsInBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;
    @Inject
    private static ItemService itemService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> items = bucketService.getAllItems(0L);
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/views/bucketItems.jsp").forward(req, resp);

    }
}
