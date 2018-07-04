package com.mng.chat.services;

import com.danielcs.webserver.socket.annotations.InjectionPoint;
import com.mng.chat.repository.dao.RoomDAO;
import java.util.*;

public class RoomService {

    private RoomDAO roomRepository;
    private Map<String, Set<Integer>> rooms = new HashMap<>();

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

    public boolean attemptRoomJoin(String prevRoom, String nextRoom, int userId) {
        initRooms();
        if (rooms.keySet().contains(nextRoom)) {
            rooms.get(prevRoom).remove(userId);
            rooms.get(nextRoom).add(userId);
            return true;
        }
        return false;
    }

    public boolean attemptRoomJoin(String firstRoom, int userId) {
        initRooms();
        if (rooms.keySet().contains(firstRoom)) {
            rooms.get(firstRoom).add(userId);
            return true;
        }
        return false;
    }

    public Set<Integer> getUsersInRoom(String room) {
        return rooms.get(room);
    }
}
