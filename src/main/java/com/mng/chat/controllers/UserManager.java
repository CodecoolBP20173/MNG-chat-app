package com.mng.chat.controllers;

import com.danielcs.webserver.socket.SocketContext;
import com.danielcs.webserver.socket.annotations.OnMessage;
import com.danielcs.webserver.socket.annotations.SocketController;
import com.mng.chat.services.RoomService;
import com.mng.chat.services.UserService;

@SocketController
public class UserManager {

    private RoomService roomService;
    private UserService userService;

    public UserManager(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    @OnMessage(route = "join")
    public void joinRoom(SocketContext ctx, String roomName) {
        Object currentRoom = ctx.getProperty("currentRoom");
        int userId = (int)ctx.getProperty("id");
        boolean joined;

        if (currentRoom == null) {
            joined = roomService.attemptRoomJoin(roomName, userId);
        } else {
            joined = roomService.attemptRoomJoin(currentRoom.toString(), roomName, userId);
        }

        if (joined) {
            ctx.leaveAllRooms();
            ctx.joinRoom(roomName);
            ctx.setProperty("currentRoom", roomName);
            ctx.emitToRoom(roomName, "join", roomService.getUsersInRoom(roomName));
        } else {
            ctx.reply("DENIED");
        }
    }

    @OnMessage(route = "disconnect")
    public void onDisconnect(SocketContext ctx) {
        userService.logoutUser((int)ctx.getProperty("id"));
        ctx.emit("users", userService.getUsers());
    }
}
