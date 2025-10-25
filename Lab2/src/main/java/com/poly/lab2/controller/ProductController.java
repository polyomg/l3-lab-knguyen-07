package com.poly.lab2.controller;

import com.poly.lab2.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("items")
public class ProductController {
    @ModelAttribute("items")
    public List<Product> getItems() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("A", 1.0));
        list.add(new Product("B", 12.0));
        return list;
    }

    @GetMapping("/product/form")
    public String form(Model model) {
        Product p = new Product("iPhone 30", 5000.0);
        model.addAttribute("pForm", p); // ?1: product mặc định
        model.addAttribute("pSave", new Product()); // để tránh null ở view
        return "product/form";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute("pSave") Product pSave,
                       @ModelAttribute("items") List<Product> items,
                       Model model) {
        items.add(pSave); // thêm sản phẩm mới vào danh sách
        model.addAttribute("pForm", new Product("iPhone 30", 5000.0)); // giữ lại giá trị mặc định
        return "product/form";
    }
}
