package com.app.wagon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.wagon.service.ProductService;
import com.app.wagon.util.TemplatePathUtil;

@Controller
public class HomeController {

    @Autowired
    private TemplatePathUtil path;

    @Autowired
    ProductService pService;

    @RequestMapping({ "/index", "" })
    public String getHomeView(Model model) {
        model.addAttribute("pList", pService.findAll());
        return path.getShopTemplateViewName("index");
    }

}
