package com.epam.training.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String title;

    private String genre;

    private int minLength;

    public Movie(String title, String genre, int minLength) {
        this.title = title;
        this.genre = genre;
        this.minLength = minLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return minLength == movie.minLength && Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, minLength);
    }
}
