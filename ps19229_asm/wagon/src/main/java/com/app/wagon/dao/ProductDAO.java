package com.app.wagon.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.wagon.model.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);
}
