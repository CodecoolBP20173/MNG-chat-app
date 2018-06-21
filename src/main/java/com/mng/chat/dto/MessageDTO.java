package com.mng.chat.dto;

public class MessageDTO {

    private String name;
    private String content;

    public MessageDTO(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public MessageDTO() {
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
