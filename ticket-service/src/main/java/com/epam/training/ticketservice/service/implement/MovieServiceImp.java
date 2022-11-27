package com.epam.training.ticketservice.service.implement;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImp  implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> findMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    @Override
    public void deleteMovieByTitle(String title) {
        movieRepository.deleteMovieByTitle(title);
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }
}
