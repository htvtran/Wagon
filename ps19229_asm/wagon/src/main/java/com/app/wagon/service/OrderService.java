package com.app.wagon.service;

import java.util.List;

import com.app.wagon.model.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {
    Order findById(Integer id);

    List<Order> findAll();

    List<Order> findByUsername(String username);

    Order create(JsonNode orderData);
}
