package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie save(Movie movie);

    Optional<Movie> findMovieByTitle(String title);

    void deleteMovieByTitle(String title);

    List<Movie> findAll();
}
