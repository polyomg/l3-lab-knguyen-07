package com.poly.lab8.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("mail", new MailService.Mail());
        return "mail/form";
    }

    // send immediately
    @PostMapping("/send")
    public String sendNow(@ModelAttribute("mail") MailService.Mail mail, Model model) {
        try {
            mailService.send(mail);
            model.addAttribute("message", "Mail của bạn đã được gửi đi");
        } catch (Exception e) {
            model.addAttribute("message", "Error: " + e.getMessage());
        }
        return "mail/form";
    }

    // push to queue
    @PostMapping("/queue")
    public String sendQueue(@ModelAttribute("mail") MailService.Mail mail, Model model) {
        mailService.push(mail);
        model.addAttribute("message", "Mail của bạn đã được xếp vào hàng đợi");
        return "mail/form";
    }

    // simple endpoint per lab
    @GetMapping("/send-simple")
    @ResponseBody
    public String sendSimple() {
        try {
            mailService.push("receiver@gmail.com", "Subject", "Body");
            return "Mail của bạn đã được xếp vào hàng đợi";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}