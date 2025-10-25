package com.poly.lab8.auth;

import com.poly.lab8.auth.entity.Account;
import com.poly.lab8.auth.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("message", "");
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {
        Account user = accountService.findById(username);
        if (user == null) {
            model.addAttribute("message", "Invalid username!");
            return "auth/login";
        }
        if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
            return "auth/login";
        }
        session.setAttribute("user", user);
        model.addAttribute("message", "Login successfully!");

        String securityUri = (String) session.getAttribute("securityUri");
        if (securityUri != null) {
            session.removeAttribute("securityUri");
            return "redirect:" + securityUri;
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("user");
        return "redirect:/auth/login";
    }
}
