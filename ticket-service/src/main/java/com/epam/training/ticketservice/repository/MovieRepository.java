package com.epam.training.ticketservice.repository;

import java.util.Optional;

import com.epam.training.ticketservice.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    Optional<Movie> findMovieByTitle(String title);

    void deleteMovieByTitle(String title);

    Iterable<Movie> findAll();

}
