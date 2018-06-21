package com.mng.chat.services;

import com.danielcs.webserver.socket.annotations.Configuration;
import com.danielcs.webserver.socket.annotations.Dependency;

@Configuration
public class AppConfig {

    @Dependency
    public DataManager getDataManager(){
        return new DataManager("mng_chat");
    }

    @Dependency
    public MessageService getMessageService(){
        return new MessageService();
    }

    @Dependency
    public RoomService getRoomService(){
        return new RoomService();
    }

    @Dependency
    public UserService userService() {
        return new UserService();
    }
}
