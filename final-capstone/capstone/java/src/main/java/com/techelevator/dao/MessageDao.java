package com.techelevator.dao;

import com.techelevator.model.Message;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface MessageDao {
    Message findById(int messageId);

    List<Message> getAllMessages();

    List<Message> findMessagesBySenderId(int senderId);

    List<Message> findMessagesByReceiverId(int receiverId);

    Message saveMessage(Message message);

    Message updateMessage(Message message, int messageId);

    void deleteMessage(int messageId);
}
