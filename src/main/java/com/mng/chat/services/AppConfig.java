package com.mng.chat.services;

import com.danielcs.webserver.socket.annotations.Configuration;
import com.danielcs.webserver.socket.annotations.Dependency;

@Configuration
public class AppConfig {

    @Dependency
    public DataManager getDataManager(){
        return new DataManager("mng_chat");
    }
}
