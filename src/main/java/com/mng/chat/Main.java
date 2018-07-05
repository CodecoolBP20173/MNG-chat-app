package com.mng.chat;

import com.danielcs.webserver.Server;
import com.danielcs.webserver.socket.SocketServer;

public class Main {

    public static void main(String[] args) {
        Server server = new SocketServer(8000, "com.mng.chat.controllers");
        server.start();
    }
}