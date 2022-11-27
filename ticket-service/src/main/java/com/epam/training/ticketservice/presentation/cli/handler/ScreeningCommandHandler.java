package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.common.TicketConstants;
import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.helper.AccountHelper;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class ScreeningCommandHandler {

    @Autowired
    private AccountHelper accountHelper;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ScreeningService screeningService;

    @Transactional
    @ShellMethod(value = "Create Screening", key = "create screening")
    public String createScreening(final String movieTitle, final String roomName, final String startDate) {
        final String overlapping = "There is an overlapping screening";
        final String denied = "This would start in the break period after another screening in this room";

        if (accountHelper.isAdmin()) {
            Optional<Movie> movie = movieService.findMovieByTitle(movieTitle);
            Optional<Room> room = roomService.findRoomByName(roomName);
            boolean existMovieAndRoom = movie.isPresent() && room.isPresent();
            if (!existMovieAndRoom) {
                return "Movie or Room not found!";
            }
            LocalDateTime start = DateUtil.format(startDate);
            Optional<Screening> previousScreening = screeningService.findPreviousScreening(start);

            if (previousScreening.isPresent() && previousScreening.get().getRoomName().equals(roomName)) {
                Optional<Movie> previousMovie = movieService.findMovieByTitle(previousScreening.get().getMovieTitle());
                LocalDateTime screenStartTime = previousScreening.get().getStart();

                LocalDateTime previousMovieFinal = screenStartTime.plusMinutes(previousMovie.get().getMinLength());
                LocalDateTime previousMovieWithBreak = previousMovieFinal.plusMinutes(10);

                if (previousMovieFinal.isAfter(start)) {
                    return overlapping;
                }

                if (previousMovieWithBreak.isAfter(start)) {
                    return denied;
                }
            }

            Screening savedScreening = screeningService.save(new Screening(movieTitle, roomName, start));
            return String.format("Created screening %s %s %s", savedScreening.getMovieTitle(), savedScreening.getRoomName(), savedScreening.getStart());
        } else {
            return TicketConstants.UNAUTHORIZED;
        }
    }

    @Transactional
    @ShellMethod(value = "Delete Screening", key = "delete screening")
    public String deleteScreening(final String movieTitle, final String roomName, final String startDate) {
        if (accountHelper.isAdmin()) {
            screeningService.deleteScreeningByMovieTitleAndRoomNameAndStart(movieTitle, roomName, DateUtil.format(startDate));
            return String.format("Deleted screening %s %s %s", movieTitle, roomName, DateUtil.format(startDate));
        } else {
            return TicketConstants.UNAUTHORIZED;
        }
    }

    @ShellMethod(value = "List Screenings", key = "list screenings")
    public String listMovies() {
        List<Screening> screenings = screeningService.findAll();
        if (screenings.isEmpty()) {
            return "There are no screenings";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Screening s : screenings) {
            final Movie movie = movieService.findMovieByTitle(s.getMovieTitle()).get();
            final String screening = String.format("%s (%s, %s minutes), screened in room %s, at %s",
                    s.getMovieTitle(), movie.getGenre(), movie.getMinLength(), s.getRoomName(), DateUtil.format(s.getStart()));
            stringBuilder.append(screening);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}