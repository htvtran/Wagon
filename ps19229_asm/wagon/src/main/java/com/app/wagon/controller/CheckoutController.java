package com.app.wagon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController extends BaseViewController {

    @GetMapping({ "/account/checkout" })
    public String getView() {
        return getShopTemplateViewName("checkout");
    }

}
