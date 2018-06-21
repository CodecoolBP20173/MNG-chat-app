package com.mng.chat.services;

import com.danielcs.webserver.socket.annotations.InjectionPoint;
import com.mng.chat.dto.UserDTO;
import com.mng.chat.models.Room;
import com.mng.chat.models.User;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

public class RoomService {
    Map<String, Set<User>> rooms = new HashMap<>();
    EntityManager em;

    @InjectionPoint
    public void setEm(DataManager dm) {
        em = dm.getEntityManager();
        List<Room> rooms = em.createNamedQuery("getAllRooms", Room.class).getResultList();
        for (Room room : rooms) {
            this.rooms.put(room.getName(), new HashSet<User>());
        }
    }

    public void join(String roomName, User user) {
        if (rooms.get(roomName)!=null) {
            rooms.get(roomName).add(user);
        }
    }

    public Set<String> getUsers(String roomName) {
        System.out.println(this.rooms.get(roomName));
        return this.rooms.get(roomName).stream().map(User::getNickName).collect(Collectors.toSet());
    }

    public Set<String> getAllRooms() {
        return rooms.keySet();
    }
}
