package com.app.wagon.service;

import java.util.List;

import com.app.wagon.model.Category;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Integer id);

    Category findByName(String name);
}
