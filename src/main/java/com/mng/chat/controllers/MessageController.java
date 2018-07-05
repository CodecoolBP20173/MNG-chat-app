package com.mng.chat.controllers;

import com.danielcs.webserver.socket.SocketContext;
import com.danielcs.webserver.socket.annotations.OnMessage;
import com.danielcs.webserver.socket.annotations.SocketController;
import com.mng.chat.models.*;
import com.mng.chat.repository.dao.MessageDAO;
import com.mng.chat.services.RoomService;

@SocketController
public class MessageController {

    private MessageDAO messageRepository;
    private RoomService roomService;

    public MessageController(MessageDAO messageRepository, RoomService roomService) {
        this.messageRepository = messageRepository;
        this.roomService = roomService;
    }

    @OnMessage(route = "private/send", type = MessageDTO.class)
    public void privateChat(SocketContext ctx, MessageDTO message) {
        User user = new User(
                (int)ctx.getProperty("id"),
                ctx.getProperty("nickname").toString()
        );
        Message msg = new Message(message.getContent(), message.getName(), TargetType.USER, user.getId());
        saveMessage(msg);
        MessageDTO messageDTO = new MessageDTO(
                msg.getId(), String.valueOf(user.getId()), message.getContent()
        );
        ctx.reply(msg.getId());
        ctx.sendToUser("id", Integer.valueOf(message.getName()), "private/get", messageDTO);
    }

    @OnMessage(route = "room/chat", type = MessageDTO.class)
    public void roomChat(SocketContext ctx, MessageDTO message) {
        String currentRoom = message.getName();
        User user = new User(
                (int)ctx.getProperty("id"),
                ctx.getProperty("nickname").toString()
        );
        if (!roomService.isUserInRoom(user, currentRoom)) {
            return;
        }
        Message msg = new Message(message.getContent(), currentRoom, TargetType.ROOM, user.getId());
        saveMessage(msg);
        MessageDTO messageDTO = new MessageDTO(
                msg.getId(), user.getNickname(), message.getContent()
        );
        ctx.emitToRoom(currentRoom, "room/chat", new RoomEmit(currentRoom, messageDTO));
    }

    @OnMessage(route = "chat")
    public void publicChat(SocketContext ctx, String message) {
        Message msg = new Message(message, null, null, (int)ctx.getProperty("id"));
        saveMessage(msg);
        MessageDTO messageDTO = new MessageDTO(
                msg.getId(), ctx.getProperty("nickname").toString(), message
        );
        ctx.emit("chat", messageDTO);
    }

    private void saveMessage(Message msg) {
        int msgId = messageRepository.saveMessage(msg);
        msg.setId(msgId);
    }
}
