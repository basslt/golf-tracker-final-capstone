package com.techelevator.controller;

import com.techelevator.dao.MessageDao;
import com.techelevator.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @GetMapping("/messages/{id}")
    public Message getMessageById(@PathVariable("id") int messageId) {
        Message message = messageDao.findById(messageId);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found.");
        } else {
            return message;
        }
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    @GetMapping("/users/{userId}/received-messages")
    public List<Message> getReceivedMessagesByUser(@PathVariable("userId") int userId){
        return messageDao.findMessagesByReceiverId(userId);
    }

    @GetMapping("/users/{userId}/sent-messages")
    public List<Message> getSentMessagesByUser(@PathVariable("userId") int userId){
        return messageDao.findMessagesBySenderId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {
        return messageDao.saveMessage(message);
    }

    @PutMapping("/messages/{id}")
    public Message updateMessage(@PathVariable("id") int messageId, @RequestBody Message message) {
        Message updatedMessage = messageDao.updateMessage(message, message.getMessageId());
        if (updatedMessage == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
        } else {
            return updatedMessage;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable("id") int messageId) {
        messageDao.deleteMessage(messageId);
    }
}
