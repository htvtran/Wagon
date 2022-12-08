package com.app.wagon.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.wagon.model.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

    public final static String GET_ORDER_BY_USER = "SELECT o FROM Order o  WHERE o.user.username like :name";

    @Query(value = GET_ORDER_BY_USER)
    Optional<List<Order>> findByUserWithUsername(@Param("name") String name);
}
