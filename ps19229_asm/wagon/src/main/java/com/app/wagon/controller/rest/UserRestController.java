package com.app.wagon.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.wagon.model.User;
import com.app.wagon.model.UserService;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/rest/account/{username}")
    public User getUser(@PathVariable String username) {
        System.out.println("request user");
        return userService.findByUsername(username);
    }

    @GetMapping("/users")
    public String getUserView() {
        return "user page";
    }
}
