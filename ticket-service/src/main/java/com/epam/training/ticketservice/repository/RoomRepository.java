package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    Optional<Room> findRoomByName(String name);

    void deleteRoomByName(String name);

    Iterable<Room> findAll();
}
