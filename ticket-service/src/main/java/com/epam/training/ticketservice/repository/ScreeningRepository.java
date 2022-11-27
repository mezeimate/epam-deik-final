package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.Screening;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ScreeningRepository extends CrudRepository<Screening, Long> {

    @Query("SELECT sc FROM Screening sc WHERE sc.start = (SELECT MAX(subsc.start) FROM Screening subsc WHERE subsc.start < :start)")
    Optional<Screening> findPreviousScreening(@Param("start") LocalDateTime start);

    @Query("SELECT sc FROM Screening sc WHERE sc.start = (SELECT MIN(subsc.start) FROM Screening subsc WHERE subsc.start > :start)")
    Optional<Screening> findNextScreening(@Param("start") LocalDateTime start);

    void deleteScreeningByMovieTitleAndRoomNameAndStart(final String movieTitle, final String roomName, final LocalDateTime startDate);

}
