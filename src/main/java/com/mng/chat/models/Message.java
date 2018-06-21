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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public TargetType getType() {
        return type;
    }

    public void setType(TargetType type) {
        this.type = type;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }


}
