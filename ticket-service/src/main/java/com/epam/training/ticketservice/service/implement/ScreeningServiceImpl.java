package com.epam.training.ticketservice.service.implement;

import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Override
    public Screening save(Screening screening) {
        return screeningRepository.save(screening);
    }

    @Override
    public List<Screening> findAll() {
        List<Screening> screenings = new ArrayList<>();
        screeningRepository.findAll().forEach(screenings::add);
        return screenings;
    }

    @Override
    public Optional<Screening> findPreviousScreening(LocalDateTime start) {
        return screeningRepository.findPreviousScreening(start);
    }

    @Override
    public Optional<Screening> findNextScreening(LocalDateTime start) {
        return screeningRepository.findNextScreening(start);
    }

    @Override
    public void deleteScreeningByMovieTitleAndRoomNameAndStart(final String movieTitle, final String roomName, final LocalDateTime startDate) {
        screeningRepository.deleteScreeningByMovieTitleAndRoomNameAndStart(movieTitle, roomName, startDate);
    }
}
