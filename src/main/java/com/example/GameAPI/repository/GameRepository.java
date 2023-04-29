package com.example.GameAPI.repository;

import com.example.GameAPI.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
