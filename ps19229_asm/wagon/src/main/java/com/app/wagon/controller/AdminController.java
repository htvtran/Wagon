package com.app.wagon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController extends BaseViewController<AdminController> {

    @GetMapping("/admin")
    public String getAdminPage() {
        return "redirect:/admin/home.html#!/home";
    }
}
