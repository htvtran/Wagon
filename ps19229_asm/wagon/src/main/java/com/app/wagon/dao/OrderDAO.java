package com.app.wagon.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.wagon.model.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

    Optional<List<Order>> findByUser(String username);
}
