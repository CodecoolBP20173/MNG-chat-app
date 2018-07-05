package com.mng.chat.repository.dao;

import com.danielcs.webserver.socket.annotations.InjectionPoint;
import com.mng.chat.models.Message;
import com.mng.chat.repository.SQLUtils;

public class MessageRepository implements MessageDAO {

    private SQLUtils db;

    @InjectionPoint
    public void setDb(SQLUtils db) {
        this.db = db;
    }

    @Override
    public Integer saveMessage(Message message) {
        return message.getType() != null ? db.fetchOne(
                "INSERT INTO message (content, target_address, type, owner_id)" +
                        "    VALUES (?, ?, ?, ?::INTEGER) RETURNING id;",
                rs -> rs.getInt("id"),
                message.getContent(), message.getTargetAddress(),
                String.valueOf(message.getType()), String.valueOf(message.getOwnerId()))
                :
                db.fetchOne(
                        "INSERT INTO message (content, owner_id)" +
                                "    VALUES (?, ?::INTEGER) RETURNING id;",
                        rs -> rs.getInt("id"),
                        message.getContent(), String.valueOf(message.getOwnerId()));
    }
}
