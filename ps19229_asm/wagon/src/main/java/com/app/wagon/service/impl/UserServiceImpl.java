package com.app.wagon.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.wagon.dao.UserDAO;
import com.app.wagon.model.User;
import com.app.wagon.model.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO dao;

    @Override
    @Transactional
    public User findByUsername(String username) {
        User u = dao.findByUsername(username).get();
        return u;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return dao.findAll();
    }

}
