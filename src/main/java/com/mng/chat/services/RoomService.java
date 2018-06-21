package com.mng.chat.services;

import com.mng.chat.models.Room;
import com.mng.chat.models.User;

import javax.persistence.EntityManager;
import java.util.*;

public class RoomService {
    Map<String, Set<User>> rooms = new HashMap<>();
    EntityManager em;

    public RoomService(DataManager dm) {
        em = dm.getEntityManager();
        List<Room> rooms = em.createNamedQuery("getAllRooms").getResultList();
        for (Room room : rooms) {
            this.rooms.put(room.getName(), new HashSet<User>());
        }
    }

    public void join(String roomName, User user) {
        this.rooms.get(roomName).add(user);
    }

    public Set<User> getUsers(String roomName) {
        return this.rooms.get(roomName);
    }
}
