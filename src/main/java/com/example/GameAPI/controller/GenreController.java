package com.example.GameAPI.controller;

import com.example.GameAPI.model.Genre;
import com.example.GameAPI.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Get Request for a Genre Object with an id matching the provided genreId
     *
     * @param genreId The id that returned Genre Object should have
     * @return A Genre object from the Genre table with an id matching genreId
     */
    // http://localhost:9094/api/genres/1
    @GetMapping(path = "/genres/{genreId}")
    public Genre getGenreById(@PathVariable Long genreId) {
        return genreService.findGenreById(genreId);
    }

    /**
     * Post Requests to create a new genre object using the data provide in the genreObject
     *
     * @param genreObject The data used to create new genre entry
     * @return The newly created genre entry
     */
    // http://localhost:9094/api/genres/new
    @PostMapping(path = "/genres/new")
    public Genre createGenre(@RequestBody Genre genreObject) {
        return genreService.createGenre(genreObject);
    }

    /**
     * Get Request for all genres in the genre entity
     *
     * @return A list of all genres
     */
    // http://localhost:9094/api/genres
    @GetMapping(path = "/genres")
    public List<Genre> getAllGenres() {
        return genreService.findAllGenres();
    }

    /**
     * Put Request to update description of genre with id matching provided genreId
     *
     * @param genreId     The id of the genre to update
     * @param genreObject The data to update the genre
     * @return The updated genre
     */
    // http://localhost:9094/api/genres
    @PutMapping(path = "/genres/{genreId}")
    public Genre updateGenreDescriptionById(@PathVariable Long genreId, @RequestBody Genre genreObject) {
        return genreService.updateGenreDescriptionById(genreId, genreObject);
    }
}
