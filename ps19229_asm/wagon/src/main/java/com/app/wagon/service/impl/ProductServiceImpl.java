package com.app.wagon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.wagon.dao.CategoryDAO;
import com.app.wagon.dao.ProductDAO;
import com.app.wagon.model.Category;
import com.app.wagon.model.Product;
import com.app.wagon.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO pDao;

    @Autowired
    CategoryDAO catDao;

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        return pDao.findAll();
    }

    @Override
    public List<Product> findByCategoryId(Integer catId) {
        Category cat = catDao.findById(catId).orElse(null);

        if (cat != null)
            return cat.getProdList();
        return null;
    }

    @Override
    public List<Product> findByCategoryId(String catId) {
        return findByCategoryId(Integer.parseInt(catId));
    }

    @Override
    public Product findById(Integer prodId) {
        Product obj = pDao.findById(prodId).orElse(null);
        return obj;
    }

    @Override
    public Product findByName(String name) {
        // TODO Auto-generated method stub
        return pDao.findByName(name).orElseGet(() -> {
            throw new RuntimeException("Can't find product by name = " + name);
        });
    }

    @Override
    public Product create(Product p) {

        return pDao.save(p);
    }

}
