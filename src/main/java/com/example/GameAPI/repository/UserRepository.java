package com.example.GameAPI.repository;

import com.example.GameAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // SELECT * FROM users WHERE username = name
    Optional<User> findUserByUsername(String name);
}
