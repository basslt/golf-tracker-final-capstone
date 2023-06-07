package com.techelevator.dao;

import com.techelevator.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMessageDao implements MessageDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Message findById(int messageId) {
        Message message = null;
        String query = "SELECT * FROM Message WHERE message_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, messageId);
            if (results.next()) {
                message = mapRowToMessage(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return message;
    }


    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM Message";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query);
            while (results.next()) {
                Message message = mapRowToMessage(results);
                messages.add(message);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return messages;
    }

    @Override
    public List<Message> findMessagesBySenderId(int senderId) {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM Message WHERE sender_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, senderId);
            while (results.next()) {
                Message message = mapRowToMessage(results);
                messages.add(message);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return messages;
    }

    @Override
    public List<Message> findMessagesByReceiverId(int receiverId) {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM Message WHERE receiver_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, receiverId);
            while (results.next()) {
                Message message = mapRowToMessage(results);
                messages.add(message);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return messages;
    }

    @Override
    public Message saveMessage(Message message) {
        Message newMessage =null;
        String query = "INSERT INTO Message (sender_id, receiver_id, league_id, content, type, timestamp) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING message_id";
        try {
            int newMessageId = jdbcTemplate.queryForObject(query, int.class, message.getSenderId(), message.getReceiverId(),
                    message.getLeagueId(), message.getContent(), message.getType(), message.getTimestamp());
            newMessage = findById(newMessageId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return newMessage;
    }

    @Override
    public void updateMessage(Message message, int messageId) {
        String query = "UPDATE Message SET sender_id = ?, receiver_id = ?, league_id = ?, content = ?, type = ?, timestamp = ? " +
                "WHERE message_id = ?";
        try {
            jdbcTemplate.update(query, message.getSenderId(), message.getReceiverId(), message.getLeagueId(),
                    message.getContent(), message.getType(), message.getTimestamp(), message.getMessageId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
    }

    @Override
    public void deleteMessage(int messageId) {
        String query = "DELETE FROM Message WHERE message_id = ?";
        try {
            jdbcTemplate.update(query, messageId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
    }



    public Message mapRowToMessage(SqlRowSet rowSet) {
        Message message = new Message();
        message.setMessageId(rowSet.getInt("message_id"));
        message.setSenderId(rowSet.getInt("sender_id"));
        message.setReceiverId(rowSet.getInt("receiver_id"));
        if (rowSet.getObject("league_id") != null) {
            message.setLeagueId(rowSet.getInt("league_id"));
        } else {
            message.setLeagueId(null);
        }
        message.setContent(rowSet.getString("content"));
        message.setType(rowSet.getString("type"));
        message.setTimestamp(rowSet.getTimestamp("timestamp"));
        return message;
    }

}
