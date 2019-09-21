package mateacademy.internetshop.web.filters;

import java.io.IOException;

import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mateacademy.internetshop.lib.Inject;
import mateacademy.internetshop.model.User;
import mateacademy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {
    private static Logger logger = Logger.getLogger(AuthenticationFilter.class);

    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servReq, ServletResponse servResp, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servReq;
        HttpServletResponse resp = (HttpServletResponse) servResp;
        if (req.getCookies() == null) {
            processUnAuthenticated(req, resp);
            return;
        }
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                Optional<User> user = userService.getByToken(cookie.getValue());
                if (user.isPresent()) {
                    logger.info("User " + user.get().getLogin() + " was authenticated");
                    filterChain.doFilter(servReq, servResp);
                    return;
                }
            }
        }
        logger.info("User was not authenticated");
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    private void processUnAuthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
    }
}
