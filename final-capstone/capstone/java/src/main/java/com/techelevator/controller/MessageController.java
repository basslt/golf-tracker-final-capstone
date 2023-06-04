package com.techelevator.controller;

import com.techelevator.dao.MessageDao;
import com.techelevator.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
public class MessageController {
    //private final MessageDao messageDao;

//    @Autowired
//    public MessageController(MessageDao messageDao) {
//        this.messageDao = messageDao;
//    }

    @Autowired
    private MessageDao messageDao;

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") int messageId) throws ChangeSetPersister.NotFoundException {
        Message message = messageDao.findById(messageId);
        if (message == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

//    @GetMapping("/messages")
//    public ResponseEntity<List<Message>> getAllMessages() {
//        List<Message> messages = messageDao.getAllMessages();
//        return ResponseEntity.ok(messages);
//    }

//    @GetMapping("/users/{userId}/sent-messages")
//    public ResponseEntity<List<Message>> getSentMessagesByUser(@PathVariable("userId") int userId) {
//        List<Message> messages = messageDao.findMessagesBySenderId(userId);
//        return ResponseEntity.ok(messages);
//    }

//    @GetMapping("/users/{userId}/received-messages")
//    public ResponseEntity<List<Message>> getReceivedMessagesByUser(@PathVariable("userId") int userId) {
//        List<Message> messages = messageDao.findMessagesByReceiverId(userId);
//        return ResponseEntity.ok(messages);
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {
        return messageDao.saveMessage(message);
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<Void> updateMessage(@PathVariable("id") int messageId, @RequestBody Message message) {
        message.setMessageId(messageId);
        messageDao.updateMessage(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") int messageId) {
        messageDao.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
