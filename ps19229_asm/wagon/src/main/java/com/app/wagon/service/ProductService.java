package com.app.wagon.service;

import java.util.List;

import com.app.wagon.model.Product;

public interface ProductService {

    List<Product> findAll();

    List<Product> findByCategoryId(Integer catId);

    Product findById(Integer prodId);

    Product findByName(String name);

    List<Product> findByCategoryId(String prodId);

    Product create(Product p);

    Product update(Product p);

    List<Product> findByNameLike(String key);

    void delete(Integer id);
}
