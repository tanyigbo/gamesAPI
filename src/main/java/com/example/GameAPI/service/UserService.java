package com.example.GameAPI.service;

import com.example.GameAPI.exception.InformationExistException;
import com.example.GameAPI.exception.InformationNotFoundException;
import com.example.GameAPI.model.User;
import com.example.GameAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            newUser.setPassword(userObject.getPassword());
            return userRepository.save(newUser);
        } else {
            throw new InformationExistException("Username " + userObject.getUsername() + " already in use.");
        }
    }

    /**
     * Finds the user object with a username matching the provided String
     * Throws an exception if a user with the provided username does not exist
     * @param username Value of expected username
     * @return A User with a matching username
     */
    public User findActiveUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }else {
            throw new InformationNotFoundException("User with username  " + username + " was not found.");
        }
    }
}
