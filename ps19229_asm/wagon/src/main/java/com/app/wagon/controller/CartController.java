package com.app.wagon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController extends BaseViewController<CartController> {

    @RequestMapping("/cart")
    public String getView() {
        return getShopTemplateViewName("cart");
    }

    @ModelAttribute("title")
    public String getTitle() {
        return getDefaultTitle();
    }
}
