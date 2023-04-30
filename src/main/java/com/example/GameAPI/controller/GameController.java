package com.example.GameAPI.controller;

import com.example.GameAPI.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }
}
