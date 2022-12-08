package com.app.wagon.model;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<User> findAll();

}
