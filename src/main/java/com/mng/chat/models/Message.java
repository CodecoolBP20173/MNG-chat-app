package com.mng.chat.models;

public class Message {

    private int id;
    private String content;
    private String targetAddress;
    private TargetType type;
    private int ownerId;

    public Message(int id, String content, String targetAddress, TargetType type, int ownerId) {
        this.id = id;
        this.content = content;
        this.targetAddress = targetAddress;
        this.type = type;
        this.ownerId = ownerId;
    }

    public Message(String content, String targetAddress, TargetType type, int ownerId) {
        this.content = content;
        this.targetAddress = targetAddress;
        this.type = type;
        this.ownerId = ownerId;
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

    public String getTargetAddress() {
        return targetAddress;
    }

    public TargetType getType() {
        return type;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
