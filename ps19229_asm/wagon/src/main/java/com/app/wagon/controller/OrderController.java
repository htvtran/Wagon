package com.app.wagon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.wagon.model.Order;
import com.app.wagon.service.OrderService;

@Controller
@RequestMapping("/account")
public class OrderController extends BaseViewController<OrderController> {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public String getOrderView(Model model) {

        model.addAttribute("history", getOrderHistory());

        return getShopTemplateViewName("orders");
    }

    @ModelAttribute("title")
    public String getTitle() {
        return getDefaultTitle();
    }

    public List<Order> getOrderHistory() {
        String name = LoginController.getCurrent().getUsername();

        return orderService.findByUsername(name);
    }

}
