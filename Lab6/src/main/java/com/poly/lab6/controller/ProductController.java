package com.poly.lab6.controller;

import com.poly.lab6.dao.CategoryDAO;
import com.poly.lab6.dao.ProductDAO;
import com.poly.lab6.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductDAO dao;

    @Autowired
    CategoryDAO cdao;

    // Hiển thị trang chính
    @RequestMapping("/product/index")
    public String index(Model model) {
        Product item = new Product();
        model.addAttribute("item", item);
        model.addAttribute("items", dao.findAll());
        model.addAttribute("cates", cdao.findAll());
        return "product/index";
    }

    // Chọn 1 sản phẩm để edit
    @RequestMapping("/product/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Product item = dao.findById(id).orElse(new Product());
        model.addAttribute("item", item);
        model.addAttribute("items", dao.findAll());
        model.addAttribute("cates", cdao.findAll());
        return "product/index";
    }

    // Thêm sản phẩm
    @RequestMapping("/product/create")
    public String create(Product item) {
        dao.save(item);
        return "redirect:/product/index";
    }

    // Cập nhật
    @RequestMapping("/product/update")
    public String update(Product item) {
        dao.save(item);
        return "redirect:/product/edit/" + item.getId();
    }

    // Xóa
    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        dao.deleteById(id);
        return "redirect:/product/index";
    }

    // HIỂN THỊ DANH SÁCH CÓ SẮP XẾP
    @RequestMapping("/product/sort")
    public String sort(Model model,
                       @RequestParam("field") Optional<String> field) {

        // Nếu chưa chọn cột -> mặc định sắp theo price
        Sort sort = Sort.by(Sort.Direction.DESC, field.orElse("price"));
        model.addAttribute("field", field.orElse("price").toUpperCase());

        List<Product> items = dao.findAll(sort);
        model.addAttribute("items", items);
        return "product/sort";
    }

    // HIỂN THỊ THEO TRANG
    @RequestMapping("/product/page")
    public String paginate(Model model,
                           @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);

        model.addAttribute("page", page);
        return "product/page";
    }

}
