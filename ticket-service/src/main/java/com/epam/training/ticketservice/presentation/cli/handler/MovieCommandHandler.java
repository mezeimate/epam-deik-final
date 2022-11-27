package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.common.TicketConstants;
import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.helper.AccountHelper;
import com.epam.training.ticketservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class MovieCommandHandler {

    @Autowired
    private MovieService movieService;

    @Autowired
    private AccountHelper accountHelper;

    @Transactional
    @ShellMethod(value = "Create Movie", key = "create movie")
    public String createMovie(final String title, final String genre, final int movieLengthMin) {
        if (accountHelper.isAdmin()) {
            final Movie movie = new Movie(title, genre, movieLengthMin);
            final Movie savedMovie = movieService.save(movie);
            return String.format("Created movie %s %s %s", savedMovie.getTitle(), savedMovie.getGenre(), savedMovie.getMinLength());
        } else {
            return "You are not signed in";
        }
    }

    @Transactional
    @ShellMethod(value = "Update Movie", key = "update movie")
    public String updateMovie(final String title, final String genre, final int movieLengthMin) {
        if (accountHelper.isAdmin()) {
            Optional<Movie> movieOptional = movieService.findMovieByTitle(title);
            if (movieOptional.isPresent()) {
                Movie movie = movieOptional.get();
                movie.setGenre(genre);
                movie.setMinLength(movieLengthMin);
                movieService.save(movie);
                return "Movie updated";
            }
            return "Movie not found";
        }
        return TicketConstants.UNAUTHORIZED;
    }

    @Transactional
    @ShellMethod(value = "Delete Movie", key = "delete movie")
    public String deleteMovie(final String title) {
        if (accountHelper.isAdmin()) {
            movieService.deleteMovieByTitle(title);
            return String.format("Deleted '%s' movie", title);
        } else {
            return TicketConstants.UNAUTHORIZED;
        }
    }

    @ShellMethod(value = "List Movies", key = "list movies")
    public String listMovies() {
        List<Movie> movies = movieService.findAll();
        if (movies.isEmpty()) {
            return "There are no movies at the moment";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Movie m : movies) {
            final String movie = String.format("%s (%s, %s minutes)", m.getTitle(), m.getGenre(), m.getMinLength());
            stringBuilder.append(movie);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}