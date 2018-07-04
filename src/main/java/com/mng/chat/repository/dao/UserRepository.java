package com.mng.chat.repository.dao;

import com.danielcs.webserver.socket.annotations.InjectionPoint;
import com.mng.chat.models.User;
import com.mng.chat.repository.SQLUtils;

public class UserRepository implements UserDAO {

    private SQLUtils db;

    @InjectionPoint
    public void setDb(SQLUtils db) {
        this.db = db;
    }

    @Override
    public User getUserByToken(String token) {
        return db.fetchOne(
                "SELECT id, nick_name FROM users WHERE token = ?;",
                rs -> new User(rs.getInt("id"), rs.getString("nick_name")),
                token
        );
    }
}
