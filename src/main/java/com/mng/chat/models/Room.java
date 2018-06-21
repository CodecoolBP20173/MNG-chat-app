package com.mng.chat.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private Set<User> admins;


}
