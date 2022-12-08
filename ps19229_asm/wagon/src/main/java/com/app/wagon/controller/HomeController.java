package com.app.wagon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.wagon.util.TemplatePathUtil;

@Controller
public class HomeController {

    @Autowired
    private TemplatePathUtil path;

    @RequestMapping({ "/index", "" })
    public String getHomeView() {
        return path.getShopTemplateViewName("index");
    }

}
