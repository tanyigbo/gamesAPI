package com.example.GameAPI.repository;

import com.example.GameAPI.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    // SELECT * FROM games WHERE name = gameName;
    Optional<Game> findGameByName(String gameName);

    // SELECT * FROM games WHERE id = gameId;
    Optional<Game> findGameByIdAndUserId(Long gameId, Long userId);
}
