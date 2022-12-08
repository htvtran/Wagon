package com.app.wagon.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.wagon.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
}
