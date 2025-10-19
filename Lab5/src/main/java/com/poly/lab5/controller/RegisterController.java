package com.poly.lab5.controller;

import com.poly.lab5.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class RegisterController {

    @Autowired
    ParamService paramService;

    @GetMapping("/account/register")
    public String registerForm() {
        return "account/register";
    }

    @PostMapping("/account/register")
    public String registerSubmit(Model model, MultipartFile photo) {
        try {
            String username = paramService.getString("username", "");
            String password = paramService.getString("password", "");
            String confirm = paramService.getString("confirm", "");

            // Kiểm tra xác nhận mật khẩu
            if (!password.equals(confirm)) {
                model.addAttribute("message", "Mật khẩu xác nhận không khớp!");
                return "account/register";
            }

            // Lưu file ảnh vào thư mục /uploads/
            File savedFile = paramService.save(photo, "/uploads");

            if (savedFile != null) {
                model.addAttribute("message", "Đăng ký thành công!");
                model.addAttribute("photoName", photo.getOriginalFilename());
                model.addAttribute("username", username);
            } else {
                model.addAttribute("message", "Vui lòng chọn ảnh!");
            }

        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi đăng ký: " + e.getMessage());
        }

        return "account/register";
    }
}
