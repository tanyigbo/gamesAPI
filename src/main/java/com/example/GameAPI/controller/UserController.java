package com.example.GameAPI.controller;

import com.example.GameAPI.model.User;
import com.example.GameAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * Attempts to create new user using request data
     * @param userObject New user data provided in request
     * @return Newly created user object
     */
    // http://localhost:9094/api/users
    @PutMapping(path = "/register")
    public User createUser(@RequestBody User userObject){
        return userService.createUser(userObject);
    }

}
