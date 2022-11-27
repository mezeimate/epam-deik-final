package com.epam.training.ticketservice.service.implement;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> findRoomByName(String name) {
        return roomRepository.findRoomByName(name);
    }

    @Override
    public void deleteRoomByName(String name) {
        roomRepository.deleteRoomByName(name);
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        return rooms;
    }
}
