package com.mng.chat.controllers;

import com.danielcs.webserver.socket.SocketContext;
import com.danielcs.webserver.socket.annotations.OnMessage;
import com.danielcs.webserver.socket.annotations.SocketController;
import com.mng.chat.models.RoomEmit;
import com.mng.chat.models.User;
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
        User user = new User(
                (int)ctx.getProperty("id"),
                ctx.getProperty("nickname").toString()
        );
        boolean joined = roomService.attemptRoomJoin(roomName, user);

        if (joined) {
            ctx.joinRoom(roomName);
            ctx.emitToRoom(roomName, "join", new RoomEmit(roomName, roomService.getUsersInRoom(roomName)));
        }
    }

    @OnMessage(route = "connect")
    public void onConnect(SocketContext ctx) {
        ctx.emit("users", userService.getUsers());
    }

    @OnMessage(route = "disconnect")
    public void onDisconnect(SocketContext ctx) {
        User user = new User(
                (int)ctx.getProperty("id"),
                ctx.getProperty("nickname").toString()
        );
        userService.logoutUser(user);
        roomService.userLeft(user);
        ctx.emit("users", userService.getUsers());
    }
}
