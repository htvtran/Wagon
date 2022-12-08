package com.app.wagon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.wagon.dao.CategoryDAO;
import com.app.wagon.model.Category;
import com.app.wagon.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO dao;

    @Override
    public List<Category> findAll() {
        List<Category> list = dao.findAll();
        return list;
    }

    @Override
    public Category findById(Integer id) {

        return dao.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Not found category");
        });
    }

    public Category findById(String id) {
        Integer key = Integer.parseInt(id);
        return findById(key);
    }

    @Override
    public Category findByName(String name) {
        return dao.findByName(name).orElseThrow(() -> {
            throw new RuntimeException("Not found category by name " + name);
        });
    }

}
