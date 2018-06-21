package com.mng.chat.controllers;

import com.danielcs.webserver.socket.SocketContext;
import com.danielcs.webserver.socket.annotations.OnMessage;
import com.danielcs.webserver.socket.annotations.SocketController;
import com.mng.chat.dto.UserDTO;
import com.mng.chat.models.User;
import com.mng.chat.services.DataManager;
import com.mng.chat.services.UserService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@SocketController
public class UserController {

    private final EntityManager em;
    private final DataManager dm;
    private final UserService userService;

    public UserController(DataManager dm, UserService userService) {
        em = dm.getEntityManager();
        this.dm = dm;
        this.userService = userService;
    }

    @OnMessage(route="login", type = User.class)
    public void login(SocketContext ctx, User user) {
        User userInDatabase;
        try {
            userInDatabase = em.createNamedQuery("getUserByEmail", User.class)
                    .setParameter("email", user.getEmail())
                    .getSingleResult();
        } catch (NoResultException e) {
            dm.persistEntity(em, user);
            userInDatabase = user;
        }
        userService.loginUser(userInDatabase);
        ctx.setProperty("user", userInDatabase);
        ctx.setProperty("name", userInDatabase.getGivenName());
        ctx.reply(new UserDTO(userInDatabase));
        ctx.emit("users", userService.getUserNames());
    }

    @OnMessage(route = "disconnect")
    public void onDisconnect(SocketContext ctx) {
        userService.logoutUser((User)ctx.getProperty("user"));
        ctx.emit("users", userService.getUserNames());
    }
}
