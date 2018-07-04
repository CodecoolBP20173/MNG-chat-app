package com.mng.chat.repository.dao;

import com.mng.chat.models.User;

public interface UserDAO {
    User getUserByToken(String token);
}
