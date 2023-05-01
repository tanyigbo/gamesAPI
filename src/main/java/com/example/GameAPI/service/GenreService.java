package com.example.GameAPI.service;

import com.example.GameAPI.exception.InformationExistException;
import com.example.GameAPI.exception.InformationNotFoundException;
import com.example.GameAPI.model.Genre;
import com.example.GameAPI.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Finds the genre object with an id matching the provided genreID
     * Throws and exception if no there is no genre found with a matching id
     *
     * @param genreId Id a genre object should have
     * @return A genre object with id matching genreId
     */
    public Genre findGenreById(Long genreId) {
        Optional<Genre> genre = genreRepository.findGenreById(genreId);
        if (genre.isPresent()) {
            return genre.get();
        } else {
            throw new InformationNotFoundException("Genre with ID " + genreId + " was not found.");
        }
    }

    /**
     * Creates a new Genre object with the data from the provided genreObject
     * If the genre does not already exist, saves the new genre object to the genreRepository
     * Throws and exception if a genre with provided name already exists
     *
     * @param genreObject A genre object containing data for new entry
     * @return The saved genre object
     */
    public Genre createGenre(Genre genreObject) {
        Optional<Genre> genre = genreRepository.findGenreByName(genreObject.getName());
        if (genre.isEmpty()) {
            return genreRepository.save(genreObject);
        } else {
            throw new InformationExistException("Genre with name " + genreObject.getName() + " already exists.");
        }
    }

    /**
     * Find and returns all genres in the genre entity
     *
     * @return List of all genres
     */
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }
}
