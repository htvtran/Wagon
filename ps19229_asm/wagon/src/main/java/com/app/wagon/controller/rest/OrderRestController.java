package com.app.wagon.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.wagon.model.Order;
import com.app.wagon.model.OrderDetail;
import com.app.wagon.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/rest")
public class OrderRestController {

    @Autowired
    OrderService oService;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/orders/from/{username}")
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

    @PostMapping(value = "/order/purchase", produces = "text/plain")
    public ResponseEntity<?> postOrder(@RequestBody JsonNode json) {
        System.out.println("accepted order");
        // System.out.println(json.toString());

        oService.create(json);
        return new ResponseEntity<String>("success purchased", HttpStatus.OK);

    }
}
