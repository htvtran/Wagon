package com.app.wagon.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.wagon.model.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);
}
