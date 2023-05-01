package com.example.GameAPI.security;

import com.example.GameAPI.model.User;
import com.example.GameAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Finds the UserDetails of the user with username matching provided String
     *
     * @param username The username of active user
     * @return UserDetails of active user
     * @throws UsernameNotFoundException if a user with matching username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findActiveUserByUsername(username);
        return new MyUserDetails(user);
    }
}
