package com.app.wagon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.wagon.model.Authority;

@Repository
public interface AuthorityDAO extends JpaRepository<Authority, Integer> {

}
