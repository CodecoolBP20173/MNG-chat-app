package com.mng.chat.repository.dao;

import com.mng.chat.models.Message;

public interface MessageDAO {
    Integer saveMessage(Message message);
}
