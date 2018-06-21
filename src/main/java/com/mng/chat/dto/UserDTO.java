package com.mng.chat.dto;

import com.mng.chat.models.User;

public class UserDTO {

    private String nickName;
    private String introduction;

    public UserDTO(User user) {
        this.nickName = user.getNickName();
        this.introduction = user.getIntroduction();
    }
}
