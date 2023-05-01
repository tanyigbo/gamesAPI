package com.example.GameAPI.repository;

import com.example.GameAPI.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    //SELECT * FROM genre WHERE id = genreId
    Optional<Genre> findGenreById(Long genreId);
}
