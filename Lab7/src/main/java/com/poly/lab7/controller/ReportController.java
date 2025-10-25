package com.poly.lab7.controller;


import com.poly.lab7.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ProductDAO dao;

    @RequestMapping("/inventory-by-category")
    public String inventory(Model model) {
        var items = dao.getInventoryByCategory();
        model.addAttribute("items", items);
        return "report/inventory-by-category";
    }
}
