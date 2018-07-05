package com.mng.chat.services;

import com.danielcs.webserver.socket.annotations.InjectionPoint;
import com.mng.chat.models.User;
import com.mng.chat.repository.dao.RoomDAO;
import java.util.*;

public class RoomService {

    private RoomDAO roomRepository;
    private Map<String, Set<User>> rooms = new HashMap<>();

    @InjectionPoint
    public void setRoomRepository(RoomDAO roomRepository) {
        this.roomRepository = roomRepository;
    }

    private void initRooms() {
        if (rooms.isEmpty()) {
            Set<String> rooms = roomRepository.getRooms();
            for (String room : rooms) {
                this.rooms.put(room, new HashSet<>());
            }
        }
    }

    public boolean attemptRoomJoin(String room, User user) {
        initRooms();
        if (rooms.keySet().contains(room)) {
            return rooms.get(room).add(user);
        }
        return false;
    }

    public Set<User> getUsersInRoom(String room) {
        return rooms.get(room);
    }

    public void userLeft(User user) {
        rooms.keySet().forEach(room -> rooms.get(room).remove(user));
    }

    public boolean isUserInRoom(User user, String room) {
        return rooms.containsKey(room) && rooms.get(room).contains(user);
    }
}
