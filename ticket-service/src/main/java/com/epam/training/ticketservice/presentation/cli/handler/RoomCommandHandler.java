package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.common.TicketConstants;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.helper.AccountHelper;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class RoomCommandHandler {

    @Autowired
    private AccountHelper accountHelper;

    @Autowired
    private RoomService roomService;

    @Autowired
    public RoomCommandHandler(final AccountHelper accountHelper, final RoomService roomService) {
        this.accountHelper = accountHelper;
        this.roomService = roomService;
    }

    @Transactional
    @ShellMethod(value = "Create Room", key = "create room")
    public String createRoom(final String name, final int rowChairs, final int columnChairs) {
        if (accountHelper.isAdmin()) {
            final Room savedRoom = roomService.save(new Room(name, rowChairs, columnChairs));
            return String.format("Created room %s %s %s", savedRoom.getName(), savedRoom.getRowChairs(), savedRoom.getColumnChairs());
        } else {
            return TicketConstants.UNAUTHORIZED;
        }
    }

    @Transactional
    @ShellMethod(value = "Update Room", key = "update room")
    public String updateRoom(final String name, final int rowChairs, final int columnChairs) {
        if (accountHelper.isAdmin()) {
            Optional<Room> roomOptional = roomService.findRoomByName(name);
            if (roomOptional.isPresent()) {
                Room room = roomOptional.get();
                room.setRowChairs(columnChairs);
                room.setColumnChairs(columnChairs);
                room.setRowChairs(rowChairs);
                room.setChairNumber(columnChairs * rowChairs);
                roomService.save(room);
                return "Room updated";
            }
            return "Room not found";
        }
        return TicketConstants.UNAUTHORIZED;
    }

    @Transactional
    @ShellMethod(value = "Delete Room", key = "delete room")
    public String deleteRoom(final String name) {
        if (accountHelper.isAdmin()) {
            roomService.deleteRoomByName(name);
            return String.format("Deleted '%s' room", name);
        } else {
            return TicketConstants.UNAUTHORIZED;
        }
    }

    @ShellMethod(value = "List Rooms", key = "list rooms")
    public String listRooms() {
        List<Room> rooms = roomService.findAll();
        if (rooms.isEmpty()) {
            return "There are no rooms at the moment";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Room r : rooms) {
            final String room = String.format("Room %s with %s seats, %s rows and %s columns", r.getName(), r.getChairNumber(), r.getRowChairs(), r.getColumnChairs());
            stringBuilder.append(room);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}