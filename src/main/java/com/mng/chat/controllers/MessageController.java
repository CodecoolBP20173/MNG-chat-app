package com.mng.chat.controllers;

import com.danielcs.webserver.socket.SocketContext;
import com.danielcs.webserver.socket.annotations.OnMessage;
import com.danielcs.webserver.socket.annotations.SocketController;
import com.mng.chat.dto.MessageDTO;
import com.mng.chat.models.Message;
import com.mng.chat.models.User;
import com.mng.chat.services.DataManager;

import javax.persistence.EntityManager;

@SocketController
public class MessageController {

    private EntityManager em;
    private DataManager dm;

    public MessageController(DataManager dm) {
        this.em = dm.getEntityManager();
        this.dm = dm;
    }

    @OnMessage(route = "private", type = MessageDTO.class)
    public void privateChat(SocketContext ctx, MessageDTO msg) {
        MessageDTO fullMsg = processMessage(ctx, msg.getContent());
        ctx.sendToUser("name", msg.getName(), "private", fullMsg);
    }

    @OnMessage(route = "room/chat")
    public void roomChat(SocketContext ctx, String msg) {
        MessageDTO fullMsg = processMessage(ctx, msg);
        String currentRoom = ctx.getCurrentRooms().toArray(new String[0])[0];
        ctx.emitToRoom(currentRoom, "room/chat", fullMsg);
    }

    @OnMessage(route = "chat")
    public void publicChat(SocketContext ctx, String message) {
        MessageDTO fullMsg = processMessage(ctx, message);
        ctx.emit("chat", fullMsg);
    }

    private MessageDTO processMessage(SocketContext ctx, String message) {
        String currentUserName = ((User)ctx.getProperty("user")).getGivenName();
        return new MessageDTO(currentUserName, message);
    }
}
