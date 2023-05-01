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
import java.util.logging.Logger;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JWTUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;
    private Logger logger = Logger.getLogger(UserService.class.getName());

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
            User newUser = new User();
            newUser.setUsername(userObject.getUsername());
            newUser.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(newUser);
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

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            logger.info("Step 1");
            System.out.println("Step 1");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            logger.info("Step 2");
            System.out.println("Step 2");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();

            logger.info("Step 3");
            System.out.println("Step 3");
            final String JWT = jwtUtils.generateJwtToken(myUserDetails);

            logger.info("Step 4");
            System.out.println("Step 4");
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(new LoginResponse("Error: Username or Password is incorrect."));
        }
    }
}
