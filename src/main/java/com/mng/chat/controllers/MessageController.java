package com.mng.chat.controllers;

import com.danielcs.webserver.socket.SocketContext;
import com.danielcs.webserver.socket.annotations.OnMessage;
import com.danielcs.webserver.socket.annotations.SocketController;
import com.mng.chat.models.MessageDTO;
import com.mng.chat.models.Message;
import com.mng.chat.models.TargetType;
import com.mng.chat.repository.dao.MessageDAO;

@SocketController
public class MessageController {

    private MessageDAO messageRepository;

    public MessageController(MessageDAO messageRepository) {
        this.messageRepository = messageRepository;
    }

    @OnMessage(route = "private", type = MessageDTO.class)
    public void privateChat(SocketContext ctx, MessageDTO message) {
        MessageDTO messageDTO = new MessageDTO(
                (int)ctx.getProperty("id"), ctx.getProperty("nickname").toString(), message.getContent()
        );
        Message msg = new Message(message.getContent(), message.getNickname(), TargetType.USER, message.getUserId());
        messageRepository.saveMessage(msg);
        ctx.sendToUser("nickname", message.getNickname(), "private", messageDTO);
    }

    @OnMessage(route = "room/chat")
    public void roomChat(SocketContext ctx, String message) {
        String currentRoom = ctx.getProperty("currentRoom").toString();
        MessageDTO messageDTO = new MessageDTO(
                (int)ctx.getProperty("id"), ctx.getProperty("nickname").toString(), message
        );
        Message msg = new Message(message, currentRoom, TargetType.ROOM, (int)ctx.getProperty("id"));
        messageRepository.saveMessage(msg);
        ctx.emitToRoom(currentRoom, "room/chat", messageDTO);
    }

    @OnMessage(route = "chat")
    public void publicChat(SocketContext ctx, String message) {
        MessageDTO messageDTO = new MessageDTO(
                (int)ctx.getProperty("id"), ctx.getProperty("nickname").toString(), message
        );
        Message msg = new Message(message, null, null, (int)ctx.getProperty("id"));
        messageRepository.saveMessage(msg);
        ctx.emit("chat", messageDTO);
    }
}
