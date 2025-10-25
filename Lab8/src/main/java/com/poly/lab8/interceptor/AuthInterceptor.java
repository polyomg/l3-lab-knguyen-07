package com.poly.lab8.interceptor;

import com.poly.lab8.auth.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        session.setAttribute("securityUri", uri);
        Account user = (Account) session.getAttribute("user");

        // URIs that require login (as in lab)
        if (uri.startsWith("/account/edit-profile") ||
                uri.startsWith("/account/change-password") ||
                uri.startsWith("/order/") ||
                uri.startsWith("/order")) {
            if (user == null) {
                response.sendRedirect("/auth/login");
                return false;
            }
        }

        // Admin-only
        if (uri.startsWith("/admin") && !uri.equals("/admin/home/index")) {
            if (user == null || !user.isAdmin()) {
                response.sendRedirect("/auth/login");
                return false;
            }
        }

        return true;
    }
}
