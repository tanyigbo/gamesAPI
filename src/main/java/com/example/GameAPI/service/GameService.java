package com.example.GameAPI.service;

import com.example.GameAPI.exception.InformationExistException;
import com.example.GameAPI.model.Game;
import com.example.GameAPI.model.Genre;
import com.example.GameAPI.model.User;
import com.example.GameAPI.repository.GameRepository;
import com.example.GameAPI.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GenreService genreService;

    @Autowired
    public GameService(GameRepository gameRepository, GenreService genreService) {
        this.gameRepository = gameRepository;
        this.genreService = genreService;
    }

    /**
     * Authenticates the user currently logged in and returns the user information
     *
     * @return The user logged-in
     */
    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Creates a new game object with the data from the provided gameObject and assigns the genre
     * If the game does not already exist, saves the new game object to the gameRepository
     * Throws and exception if a game with provided name already exists
     * @param gameObject A game object containing the date for the new entry
     * @param genreId The id of the genre is assigned to
     * @return The saved Game entry
     */
    public Game createGame(Game gameObject, Long genreId) {
        Optional<Game> game = gameRepository.findGameByName(gameObject.getName());
        if (game.isEmpty()) {
            gameObject.setGenre(genreService.findGenreById(genreId));
            gameObject.setUser(getCurrentLoggedInUser());
            return gameRepository.save(gameObject);
        } else {
            throw new InformationExistException("Game with name " + gameObject.getName() + " already exists.");
        }
    }

    public List<Game> findAllGameByUser(){
        return getCurrentLoggedInUser().getGameList();
    }

}
