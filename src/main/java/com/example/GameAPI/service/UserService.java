package com.example.GameAPI.service;

import com.example.GameAPI.exception.InformationExistException;
import com.example.GameAPI.exception.InformationNotFoundException;
import com.example.GameAPI.model.User;
import com.example.GameAPI.model.request.LoginRequest;
import com.example.GameAPI.model.response.LoginResponse;
import com.example.GameAPI.repository.UserRepository;
import com.example.GameAPI.security.JWTUtils;
import com.example.GameAPI.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }

    /**
     * Creates a new user object with the data from the provided userObject
     * If the user does not already exist, saves a new user object to the userRepository
     * Throws an exception if a user with provided username already exists
     *
     * @param userObject A userObject containing the values for a new user
     * @return The newly created user
     */
    public User createUser(User userObject) {
        Optional<User> user = userRepository.findUserByUsername(userObject.getUsername());
        if (user.isEmpty()) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("Username " + userObject.getUsername() + " already in use.");
        }
    }

    /**
     * Finds the user object with a username matching the provided String
     * Throws an exception if a user with the provided username does not exist
     *
     * @param username Value of expected username
     * @return A User with a matching username
     */
    public User findActiveUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new InformationNotFoundException("User with username  " + username + " was not found.");
        }
    }

    /**
     * Authenticates user login request and provides JWT token on login
     * Throws an exception is login request data is incorrect
     *
     * @param loginRequest User login information
     * @return JWT token
     */
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();

            final String JWT = jwtUtils.generateJwtToken(myUserDetails);

            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(new LoginResponse("Error: Username or Password is incorrect."));
        }
    }
}
