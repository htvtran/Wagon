package com.app.wagon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController extends BaseViewController {

    @RequestMapping("/cart")
    public String getView() {
        return getShopTemplateViewName("cart");
    }
}
