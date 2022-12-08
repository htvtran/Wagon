package com.app.wagon.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.wagon.model.Order;
import com.app.wagon.service.OrderService;

@RestController
@RequestMapping("/rest")
public class OrderRestController {

    @Autowired
    OrderService oService;

    @GetMapping("/order/{username}")
    public List<Order> getAllOrderOfUser(@PathVariable String username) {

        return oService.findByUsername(username);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrder() {

        return oService.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order getAllOrder(@PathVariable Integer id) {

        return oService.findById(id);
    }
}
