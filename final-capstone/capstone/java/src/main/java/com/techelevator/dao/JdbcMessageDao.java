package com.techelevator.dao;

import com.techelevator.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class JdbcMessageDao implements MessageDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Message findById(int messageId) throws ChangeSetPersister.NotFoundException {
        String query = "SELECT * FROM Message WHERE message_id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new MessageRowMapper(), messageId);
        } catch (EmptyResultDataAccessException e) {
            throw new ChangeSetPersister.NotFoundException();
        }
    }


    @Override
    public List<Message> getAllMessages() {
        String query = "SELECT * FROM Message";
        try {
            return jdbcTemplate.query(query, new MessageRowMapper());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to retrieve all messages", e) {
            };
        }
    }

    @Override
    public List<Message> findMessagesBySenderId(int senderId) {
        String query = "SELECT * FROM Message WHERE sender_id = ?";
        try {
            return jdbcTemplate.query(query, new MessageRowMapper(), senderId);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to retrieve messages by sender ID: " + senderId, e) {
            };
        }
    }

    @Override
    public List<Message> findMessagesByReceiverId(int receiverId) {
        String query = "SELECT * FROM Message WHERE receiver_id = ?";
        try {
            return jdbcTemplate.query(query, new MessageRowMapper(), receiverId);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to retrieve messages by receiver ID: " + receiverId, e) {
            };
        }
    }

    @Override
    public void saveMessage(Message message) {
        String query = "INSERT INTO Message (sender_id, receiver_id, content, timestamp) " +
                "VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(query, message.getSenderId(), message.getReceiverId(),
                    message.getContent(), message.getTimestamp());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to save message", e) {
            };
        }
    }

    @Override
    public void updateMessage(Message message) {
        String query = "UPDATE Message SET sender_id = ?, receiver_id = ?, content = ?, timestamp = ? " +
                "WHERE message_id = ?";
        try {
            jdbcTemplate.update(query, message.getSenderId(), message.getReceiverId(),
                    message.getContent(), message.getTimestamp(), message.getMessageId());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to update message with ID: " + message.getMessageId(), e) {
            };
        }
    }

    @Override
    public void deleteMessage(int messageId) {
        String query = "DELETE FROM Message WHERE message_id = ?";
        try {
            jdbcTemplate.update(query, messageId);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to delete message with ID: " + messageId, e) {
            };
        }
    }

    private static class MessageRowMapper implements RowMapper<Message> {
        @Override
        public Message mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int messageId = resultSet.getInt("message_id");
            int senderId = resultSet.getInt("sender_id");
            int receiverId = resultSet.getInt("receiver_id");
            String content = resultSet.getString("content");
            Timestamp timestamp = resultSet.getTimestamp("timestamp");
            return new Message(messageId, senderId, receiverId, content, timestamp);
        }
    }
}
