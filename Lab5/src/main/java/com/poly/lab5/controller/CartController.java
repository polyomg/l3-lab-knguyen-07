package com.poly.lab5.controller;

import com.poly.lab5.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    ShoppingCartService cartService;

    @GetMapping("/cart/view")
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("count", cartService.getCount());
        model.addAttribute("amount", cartService.getAmount());
        return "cart/view";
    }

    @GetMapping("/cart/add")
    public String addItem(@RequestParam("id") Integer id) {
        cartService.add(id);
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/remove")
    public String removeItem(@RequestParam("id") Integer id) {
        cartService.remove(id);
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart/view";
    }
}
