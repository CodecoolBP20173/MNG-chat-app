package com.mng.chat.models;

public class MessageDTO {

    private int userId;
    private String nickname;
    private String content;

    public MessageDTO(int userId, String nickname, String content) {
        this.userId = userId;
        this.nickname = nickname;
        this.content = content;
    }

    public MessageDTO() {
    }

    public int getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }
}
