package com.app.wagon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CheckoutController extends BaseViewController<CheckoutController> {

    @GetMapping({ "/account/checkout" })
    public String getView() {
        return getShopTemplateViewName("checkout");
    }

    @ModelAttribute("title")
    public String getTitle() {
        return getDefaultTitle();
    }

}
