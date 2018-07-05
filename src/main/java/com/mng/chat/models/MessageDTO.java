package com.mng.chat.models;

public class MessageDTO {

    private int id;
    private String name;
    private String content;

    public MessageDTO(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public MessageDTO() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
