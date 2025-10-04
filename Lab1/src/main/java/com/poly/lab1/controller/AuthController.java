package com.poly.lab1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private HttpServletRequest request;

    // Hiển thị form đăng nhập
    @GetMapping("/form")
    public String form() {
        return "demo/login";
    }

    // Xử lý đăng nhập
    @PostMapping("/check")
    public String login(Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("knguyen".equals(username) && "12345".equals(password)) {
            model.addAttribute("message", "Đăng Nhập Thành Công");
        } else {
            model.addAttribute("message", "Đăng nhập thất bại. Sai username hoặc password.");
        }
        return "demo/home";
    }
}
