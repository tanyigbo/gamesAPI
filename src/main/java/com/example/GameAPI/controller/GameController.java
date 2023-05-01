package com.example.GameAPI.controller;

import com.example.GameAPI.model.Game;
import com.example.GameAPI.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // http://localhost:9094/api/games/1
    @PostMapping(path = "/games/{genreId}")
    public Game creatGame(@RequestBody Game gameObject, @PathVariable Long genreId) {
        return gameService.createGame(gameObject, genreId);
    }

    // http://localhost:9094/api/games
    @GetMapping(path = "/games")
    public List<Game> findAllGames() {
        return gameService.findAllGamesByUser();
    }

    // http://localhost:9094/api/games/1
    @GetMapping(path = "/games/{gameId}")
    public Game getUserGameById(@PathVariable Long gameId) {
        return gameService.findUserGameById(gameId);
    }

    // http://localhost:9094/api/games/1
    @PutMapping(path = "/games/{gameId}")
    public Game updateUserGameRatingById(@PathVariable Long gameId, @RequestBody Game gameObject) {
        return gameService.updateUserGameRatingById(gameId, gameObject);
    }
}
