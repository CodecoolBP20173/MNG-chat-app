package com.mng.chat.services;

import com.mng.chat.models.User;

import java.util.HashSet;
import java.util.Set;

public class UserService {

    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void loginUser(User user) {
        users.add(user);
    }

    public void logoutUser(int userId) {
        users.removeIf(user -> user.getId() == userId);
    }
}
