package com.mng.chat.services;

import com.mng.chat.models.Message;
import com.mng.chat.models.Room;
import com.mng.chat.models.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MessageService {

    private Map<String,List<Message>> messages;
    private EntityManager em;

    public MessageService(DataManager dm) {
        em = dm.getEntityManager();
        List<Room> rooms = em.createNamedQuery("getAllRooms").getResultList();
        for (Room room : rooms) {
            this.messages.put(room.getName(), new ArrayList<Message>());
        }
    }

    public void addMessage(String roomName, Message message) {
        this.messages.get(roomName).add(message);
        em.persist(message);
    }

    public List<Message> getMessages(String roomName) {
        return this.messages.get(roomName);
    }
}
