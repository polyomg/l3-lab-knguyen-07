package com.poly.lab3.controller;

import com.poly.lab3.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StaffController {

    @RequestMapping("/staff/detail")
    public String detail(Model model) {
        Staff staff = Staff.builder()
                .id("knguyen@gmail.com")
                .fullname("TaKimNguyen")
                .photo("nguyen.jpg")   // gán riêng cho detail
                .level(2)
                .build();
        model.addAttribute("staff", staff);
        return "staff-detail";
    }

    @RequestMapping("/staff/list")
    public String list(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("knguyen@gmail.com").fullname("Tạ Kim Nguyên").photo("nguyen.jpg").level(0).build(),
                Staff.builder().id("hank@gmail.com").fullname("Vũ Trịnh Hoàng Anh").photo("hanh.jpg").level(1).build(),
                Staff.builder().id("nanh@gmail.com").fullname("Nhật Anh").photo("nanh.jpg").level(2).build(),
                Staff.builder().id("mnhat@gmail.com").fullname("Nguyễn Minh Nhật").photo("nhat.jpg").level(2).build(),
                Staff.builder().id("qbao@gmail.com").fullname("Mạc Quốc Bảo").photo("bao.jpg").level(1).build()
        );
        model.addAttribute("list", list);
        return "staff-list";
    }

    @RequestMapping("/staff/status")
    public String status(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("knguyen@gmail.com").fullname("Tạ Kim Nguyên").level(0).build(),
                Staff.builder().id("hank@gmail.com").fullname("Vũ Trịnh Hoàng Anh").level(1).build(),
                Staff.builder().id("nanh@gmail.com").fullname("Nhật Anh").level(2).build(),
                Staff.builder().id("mnhat@gmail.com").fullname("Nguyễn Minh Nhật").level(2).build(),
                Staff.builder().id("qbao@gmail.com").fullname("Mạc Quốc Bảo").level(1).build()
        );
        model.addAttribute("list", list);
        return "list-status";
    }

    @RequestMapping("/staff/controls")
    public String controls(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("knguyen@gmail.com").fullname("Tạ Kim Nguyên").level(0).build(),
                Staff.builder().id("hank@gmail.com").fullname("Vũ Trịnh Hoàng Anh").level(1).build(),
                Staff.builder().id("nanh@gmail.com").fullname("Nhật Anh").level(2).build(),
                Staff.builder().id("mnhat@gmail.com").fullname("Nguyễn Minh Nhật").level(2).build(),
                Staff.builder().id("qbao@gmail.com").fullname("Mạc Quốc Bảo").level(1).build()
        );
        model.addAttribute("list", list);
        return "list-controls";
    }
}
