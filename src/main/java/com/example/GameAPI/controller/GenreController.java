package com.example.GameAPI.controller;

import com.example.GameAPI.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }


}
