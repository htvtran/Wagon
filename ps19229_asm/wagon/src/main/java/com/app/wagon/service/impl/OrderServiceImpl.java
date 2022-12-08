package com.app.wagon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.wagon.dao.OrderDAO;
import com.app.wagon.model.Order;
import com.app.wagon.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDao;

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
        return orderDao.findByUser(username).get();
    }

}
