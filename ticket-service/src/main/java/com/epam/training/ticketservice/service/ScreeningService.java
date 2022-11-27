package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Screening;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface ScreeningService {

    Screening save(Screening screening);

    List<Screening> findAll();

    Optional<Screening> findPreviousScreening(LocalDateTime start);

    Optional<Screening> findNextScreening(LocalDateTime start);

    void deleteScreeningByMovieTitleAndRoomNameAndStart(final String movieTitle, final String roomName, final LocalDateTime startDate);

}
