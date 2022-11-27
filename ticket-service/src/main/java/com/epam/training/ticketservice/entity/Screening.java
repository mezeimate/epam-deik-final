package com.epam.training.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Screening implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String movieTitle;

    private String roomName;

    private LocalDateTime start;

    public Screening(String movieTitle, String roomName, LocalDateTime start) {
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.start = start;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return Objects.equals(id, screening.id) && Objects.equals(movieTitle, screening.movieTitle) && Objects.equals(roomName, screening.roomName) && Objects.equals(start, screening.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieTitle, roomName, start);
    }
}