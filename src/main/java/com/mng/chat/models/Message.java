package com.mng.chat.models;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private int id;

    private String content;

    @ManyToOne
    private User owner;

    @Enumerated(value = EnumType.STRING)
    private TargetType type;

    private String targetAddress;

    public enum TargetType{
        USER,
        ROOM
    }

}
