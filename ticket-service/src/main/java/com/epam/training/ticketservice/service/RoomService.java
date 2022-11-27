package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RoomService {

    Room save(Room room);

    Optional<Room> findRoomByName(String name);

    void deleteRoomByName(String name);

    List<Room> findAll();
}
