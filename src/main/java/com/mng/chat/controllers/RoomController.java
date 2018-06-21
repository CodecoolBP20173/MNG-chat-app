package com.mng.chat.controllers;

import com.danielcs.webserver.socket.SocketContext;
import com.danielcs.webserver.socket.annotations.OnMessage;
import com.danielcs.webserver.socket.annotations.SocketController;
import com.mng.chat.models.User;
import com.mng.chat.services.DataManager;
import com.mng.chat.services.MessageService;
import com.mng.chat.services.RoomService;

import javax.persistence.EntityManager;

@SocketController
public class RoomController {

    private EntityManager em;
    private RoomService roomService;
    private MessageService messageService;

    public RoomController(DataManager dm, RoomService roomService, MessageService messageService) {
        this.em = dm.getEntityManager();
        this.roomService = roomService;
        this.messageService = messageService;
    }

    @OnMessage(route = "join")
    public void joinRoom(SocketContext ctx, String roomName) {
        ctx.leaveAllRooms();
        ctx.joinRoom(roomName);
        ctx.reply("room/messages", messageService.getMessages(roomName));
        User user = (User)ctx.getProperty("user");
        roomService.join(roomName, user);
        ctx.emitToRoom(roomName,"join", roomService.getUsers(roomName));
    }

    @OnMessage(route = "rooms")
    public void getRooms(SocketContext ctx, String msg) {
        ctx.reply(roomService.getAllRooms());
    }
}
