package com.mng.chat.controllers;

import com.danielcs.webserver.socket.SocketContext;
import com.danielcs.webserver.socket.annotations.OnMessage;
import com.danielcs.webserver.socket.annotations.SocketController;
import com.mng.chat.dto.UserDTO;
import com.mng.chat.models.User;
import com.mng.chat.services.DataManager;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@SocketController
public class UserController {
    private EntityManager em;
    private DataManager dm;

    public UserController(DataManager dm) {
        em = dm.getEntityManager();
        this.dm = dm;
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
        ctx.setProperty("user", userInDatabase);
        ctx.setProperty("email", userInDatabase.getEmail());
        ctx.reply(new UserDTO(userInDatabase));
    }



}
