package com.mng.chat.util;

import com.danielcs.webserver.socket.AuthGuard;
import com.danielcs.webserver.socket.SocketContext;
import com.mng.chat.models.User;
import com.mng.chat.repository.dao.UserDAO;
import com.mng.chat.services.UserService;

public class Authorization implements AuthGuard {

    private UserDAO userRepository;
    private UserService userService;

    public Authorization(UserDAO userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public boolean authorize(SocketContext ctx, String token) {
        User user = userRepository.getUserByToken(token);
        if (user != null) {
            ctx.setProperty("id", user.getId());
            ctx.setProperty("nickname", user.getNickname());
            userService.loginUser(user);
            return true;
        }
        return false;
    }
}
