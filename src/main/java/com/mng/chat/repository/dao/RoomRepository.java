package com.mng.chat.repository.dao;

import com.danielcs.webserver.socket.annotations.InjectionPoint;
import com.mng.chat.repository.ModelAssembler;
import com.mng.chat.repository.SQLUtils;

import java.util.*;

public class RoomRepository implements RoomDAO {

    private SQLUtils db;
    private ModelAssembler<String> assembler = rs -> rs.getString("name");

    @InjectionPoint
    public void setDb(SQLUtils db) {
        this.db = db;
    }

    @Override
    public Set<String> getRooms() {
        return new HashSet<>(db.fetchAll("SELECT * FROM room", assembler));
    }
}
