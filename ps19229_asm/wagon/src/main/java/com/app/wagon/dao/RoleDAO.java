package com.app.wagon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.wagon.model.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, String> {

}
