package com.example.GameAPI.controller;

import com.example.GameAPI.model.User;
import com.example.GameAPI.model.request.LoginRequest;
import com.example.GameAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user using the request data
     *
     * @param userObject New user data provided in request
     * @return Newly created user object
     */
    // http://localhost:9094/auth/users/register
    @PostMapping(path = "/register")
    public User registerUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }

    /**
     * A Post Request to authenticate and log in user
     * Throws an error if user login information is incorrect
     *
     * @param loginRequest User data to authenticate
     * @return Authentication token
     */
    // http://localhost:9094/auth/users/login
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }
}
