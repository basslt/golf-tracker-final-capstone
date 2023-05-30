package com.techelevator.dao;

import com.techelevator.model.Message;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface MessageDao {
    Message findById(int messageId) throws ChangeSetPersister.NotFoundException;

    List<Message> getAllMessages();

    List<Message> findMessagesBySenderId(int senderId);

    List<Message> findMessagesByReceiverId(int receiverId);

    void saveMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(int messageId);
}
