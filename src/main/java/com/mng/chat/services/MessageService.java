package com.mng.chat.services;

import com.danielcs.webserver.socket.annotations.InjectionPoint;
import com.mng.chat.models.Message;
import com.mng.chat.models.Room;
import com.mng.chat.models.User;

import javax.persistence.EntityManager;
import java.util.*;

public class MessageService {

    private final Map<String,List<Message>> messages = new HashMap<>();
    private EntityManager em;

    @InjectionPoint
    public void setEm(DataManager dm) {
        em = dm.getEntityManager();
        List<Room> rooms = em.createNamedQuery("getAllRooms", Room.class).getResultList();
        for (Room room : rooms) {
            this.messages.put(room.getName(), new ArrayList<Message>());
        }
    }

    public void addMessage(String roomName, Message message) {
        this.messages.get(roomName).add(message);
        em.persist(message);
    }

    public MessageService() {
    }

    public List<Message> getMessages(String roomName) {
        return this.messages.get(roomName);
    }
}
