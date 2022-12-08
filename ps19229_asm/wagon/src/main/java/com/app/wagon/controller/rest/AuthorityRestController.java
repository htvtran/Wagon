package com.app.wagon.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.wagon.model.User;
import com.app.wagon.model.UserService;

@RestController
@RequestMapping("/rest")

public class AuthorityRestController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> geetCurrent() {
        return userService.findAll();
    }

}
