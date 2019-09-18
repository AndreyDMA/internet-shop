package mateacademy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.Item;
import mateacademy.internetshop.service.ItemService;

public class AddItemsController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addItems.jsp").forward(req, resp);

        Item newItem = new Item();
        newItem.setName(req.getParameter("item_name"));
        newItem.setPrice(Double.valueOf(req.getParameter("item_price")));
        itemService.create(newItem);

        resp.sendRedirect(req.getContextPath() + "/getAllItems");
    }
}
