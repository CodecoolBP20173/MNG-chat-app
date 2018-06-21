package com.mng.chat.services;

import com.mng.chat.models.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserService {

    private final Set<User> users = new HashSet<>();

    public void loginUser(User user) {
        this.users.add(user);
    }

    public void logoutUser(User user) {
        this.users.remove(user);
    }

    public Set<String> getUserNames() {
        return users.stream().map(User::getGivenName).collect(Collectors.toSet());
    }
}
