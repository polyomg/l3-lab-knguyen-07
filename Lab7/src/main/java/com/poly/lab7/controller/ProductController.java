package com.poly.lab7.controller;

import com.poly.lab7.dao.ProductDAO;
import com.poly.lab7.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO dao;

    @RequestMapping("/search")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);

        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search";
    }

    @Autowired
    com.poly.lab7.service.SessionService session;

    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {

        String keywords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", keywords);

        var pageable = org.springframework.data.domain.PageRequest.of(p.orElse(0), 5);
        var page = dao.findAllByNameLike("%" + keywords + "%", pageable);

        model.addAttribute("page", page);
        model.addAttribute("keywords", keywords);
        return "product/search-and-page";
    }
}
