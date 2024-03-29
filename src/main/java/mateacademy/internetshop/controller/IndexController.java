package mateacademy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Injector;
import org.apache.log4j.Logger;

public class IndexController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(IndexController.class);

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            logger.error("Dependency injection failed...");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
