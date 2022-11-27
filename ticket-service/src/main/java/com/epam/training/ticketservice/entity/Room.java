package com.epam.training.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private int chairNumber;

    private int rowChairs;

    private int columnChairs;

    public Room(String name, int rowChairs, int columnChairs) {
        this.name = name;
        this.rowChairs = rowChairs;
        this.columnChairs = columnChairs;
        this.chairNumber = rowChairs * columnChairs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return chairNumber == room.chairNumber && rowChairs == room.rowChairs && columnChairs == room.columnChairs && Objects.equals(id, room.id) && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, chairNumber, rowChairs, columnChairs);
    }
}
