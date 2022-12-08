package com.app.wagon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.wagon.dao.OrderDAO;
import com.app.wagon.dao.OrderDetailDAO;
import com.app.wagon.model.Order;
import com.app.wagon.model.OrderDetail;
import com.app.wagon.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDao;

    @Autowired
    OrderDetailDAO orderDetailDao;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Order findById(Integer id) {

        return orderDao.findById(id).get();
    }

    @Override
    public List<Order> findAll() {

        return orderDao.findAll();
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderDao.findByUserWithUsername(username).get();
    }

    @Override
    public Order create(JsonNode json) {
        System.out.println("accepted order");
        System.out.println(json.toString());

        Order order = mapper.convertValue(json, Order.class);
        System.out.println(order);
        orderDao.save(order);
        List<OrderDetail> orderDetailsList = new ArrayList<>();
        JsonNode node = json.get("orderDetail");
        for (JsonNode n : node) {
            System.out.println(n.toString());
            OrderDetail od = mapper.convertValue(n, OrderDetail.class);
            od.setOrder(order);
            orderDetailsList.add(od);
            System.out.println(od);
        }

        orderDetailDao.saveAll(orderDetailsList);

        System.out.println("saved to DB" + order);
        return null;
    }

}
