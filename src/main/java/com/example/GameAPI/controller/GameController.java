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

    /**
     * Post request to create a new game and assign to genre with id matching provided genreId
     *
     * @param gameObject The game object to be added to the game repository
     * @param genreId    The id of genre to be assigned to game
     * @return New added game object
     */
    // http://localhost:9094/api/games/1
    @PostMapping(path = "/games/{genreId}")
    public Game creatGame(@RequestBody Game gameObject, @PathVariable Long genreId) {
        return gameService.createGame(gameObject, genreId);
    }

    /**
     * Get request to find the list of all games belonging to active user
     *
     * @return List of Games
     */
    // http://localhost:9094/api/games
    @GetMapping(path = "/games")
    public List<Game> findAllGames() {
        return gameService.findAllGamesByUser();
    }

    /**
     * Get Request to find game belonging to active user with id matching provided gameId
     *
     * @param gameId The id of game to find
     * @return Game object
     */
    // http://localhost:9094/api/games/1
    @GetMapping(path = "/games/{gameId}")
    public Game getUserGameById(@PathVariable Long gameId) {
        return gameService.findUserGameById(gameId);
    }

    /**
     * Finds a game that belongs to active user and updates it to the data in provided gameObject
     *
     * @param gameId     The id of game to update
     * @param gameObject A game object with data to update to
     * @return Updated game object
     */
    // http://localhost:9094/api/games/1
    @PutMapping(path = "/games/{gameId}")
    public Game updateUserGameRatingById(@PathVariable Long gameId, @RequestBody Game gameObject) {
        return gameService.updateUserGameRatingById(gameId, gameObject);
    }
}
