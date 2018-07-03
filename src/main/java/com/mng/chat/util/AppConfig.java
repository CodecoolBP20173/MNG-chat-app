package com.mng.chat.util;

import com.danielcs.webserver.socket.annotations.Configuration;
import com.danielcs.webserver.socket.annotations.Dependency;
import com.mng.chat.repository.SQLUtils;
import com.mng.chat.repository.dao.*;
import com.mng.chat.services.RoomService;
import com.mng.chat.services.UserService;

@Configuration
public class AppConfig {

    @Dependency
    public SQLUtils getDataManager() {
        return new SQLUtils(
                System.getenv("DB_PATH"),
                System.getenv("DB_USER"),
                System.getenv("DB_PASSWORD")
        );
    }

    @Dependency
    public RoomDAO getRoomRepository() {
        return new RoomRepository();
    }

    @Dependency
    public MessageDAO getMessageRepository() {
        return new MessageRepository();
    }

    @Dependency
    public UserDAO getUserRepository() {
        return new UserRepository();
    }

    @Dependency
    public RoomService getMessageService() {
        return new RoomService();
    }

    @Dependency
    public UserService getUserService() {
        return new UserService();
    }
}
