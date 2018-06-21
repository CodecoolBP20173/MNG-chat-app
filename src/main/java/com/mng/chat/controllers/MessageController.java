package com.mng.chat.controllers;

import com.danielcs.webserver.socket.SocketContext;
import com.danielcs.webserver.socket.annotations.OnMessage;
import com.danielcs.webserver.socket.annotations.SocketController;
import com.mng.chat.models.Message;
import com.mng.chat.services.DataManager;

import javax.persistence.EntityManager;

@SocketController
public class MessageController {

    private EntityManager em;

    public MessageController(DataManager dm) {
        this.em = dm.getEntityManager();
    }

    @OnMessage(route="send", type = Message.class)
    public void send(SocketContext ctx, Message message) {
        if(message.getType() == Message.TargetType.USER) {
            ctx.sendToUser("email", message.getTargetAddress(), "send", message.getContent());
        } else {
          ctx.emitToRoom(message.getTargetAddress(), "send", message.getContent());
        }
        // TODO save pm to cache
        em.persist(message);

    }

}
