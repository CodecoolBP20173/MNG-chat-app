package com.mng.chat.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedQuery(name = "getAllRooms", query = "FROM Room")
public class Room {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private Set<User> admins;

    public String getName() {
        return name;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admins=" + admins +
                '}';
    }
}
