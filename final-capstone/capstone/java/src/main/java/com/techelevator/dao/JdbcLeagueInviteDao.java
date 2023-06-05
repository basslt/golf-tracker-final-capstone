package com.techelevator.dao;

import com.techelevator.model.LeagueInvite;
import com.techelevator.model.Message;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLeagueInviteDao implements LeagueInviteDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLeagueInviteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LeagueInvite findById(int leagueInviteId) {
        LeagueInvite leagueInvite = null;
        String query = "SELECT * FROM LeagueInvite WHERE league_invite_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, leagueInviteId);
            if (results.next()) {
                leagueInvite = mapRowToLeagueInvite(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return leagueInvite;
    }

    @Override
    public List<LeagueInvite> getAllLeagueInvites() {
        List<LeagueInvite> invites = new ArrayList<>();
        String query = "SELECT * FROM LeagueInvite";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query);
            while (results.next()) {
                LeagueInvite invite = mapRowToLeagueInvite(results);
                invites.add(invite);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return invites;
    }

    @Override
    public List<LeagueInvite> getAllLeagueInvitesBySenderId(int senderId) {
        List<LeagueInvite> invites = new ArrayList<>();
        String query = "SELECT * FROM LeagueInvites WHERE sender_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, senderId);
            while (results.next()) {
                LeagueInvite invite = mapRowToLeagueInvite(results);
                invites.add(invite);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return invites;
    }

    @Override
    public List<LeagueInvite> getAllLeagueInvitesByReceiverId(int receiverId) {
        List<LeagueInvite> invites = new ArrayList<>();
        String query = "SELECT * FROM Message WHERE receiver_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, receiverId);
            while (results.next()) {
                LeagueInvite invite = mapRowToLeagueInvite(results);
                invites.add(invite);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return invites;
    }

    @Override
    public LeagueInvite saveLeagueInvite(LeagueInvite leagueInvite) {
        LeagueInvite newInvite =null;
        String query = "INSERT INTO LeagueInvite (sender_id, receiver_id, league_id, content, timestamp) " +
                "VALUES (?, ?, ?, ?) RETURNING league_invite_id";
        try {
            int newInviteId = jdbcTemplate.queryForObject(query, int.class, leagueInvite.getSenderId(), leagueInvite.getReceiverId(),
                    leagueInvite.getLeagueId(), leagueInvite.getContent(), leagueInvite.getTimestamp());
            newInvite = findById(newInviteId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return newInvite;
    }

    @Override
    public LeagueInvite updateLeagueInvite(LeagueInvite leagueInvite, int id) {
        LeagueInvite updatedInvite = null;
        String query = "UPDATE LeagueInvite SET sender_id = ?, receiver_id = ?, league_id = ?, content = ?, timestamp = ? " +
                "WHERE league_invite_id = ?";
        try {
            int numberOfRows = jdbcTemplate.update(query, leagueInvite.getSenderId(), leagueInvite.getReceiverId(),
                    leagueInvite.getLeagueId(), leagueInvite.getContent(), leagueInvite.getTimestamp(), leagueInvite.getLeagueInviteId());
            if (numberOfRows == 0) {
                throw new RuntimeException("Zero rows affected, expected at least one");
            } else {
                updatedInvite = findById(leagueInvite.getLeagueId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return updatedInvite;
    }

    @Override
    public void deleteLeagueInvite(int leagueInviteId) {
        String query = "DELETE FROM LeagueInvite WHERE league_invite_id = ?";
        try {
            jdbcTemplate.update(query, leagueInviteId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
    }

    public LeagueInvite mapRowToLeagueInvite(SqlRowSet rowSet) {
        LeagueInvite leagueInvite = new LeagueInvite();
        leagueInvite.setLeagueInviteId(rowSet.getInt("league_invite_id"));
        leagueInvite.setSenderId(rowSet.getInt("sender_id"));
        leagueInvite.setReceiverId(rowSet.getInt("receiver_id"));
        leagueInvite.setLeagueId(rowSet.getInt("league_id"));
        leagueInvite.setContent(rowSet.getString("content"));
        leagueInvite.setTimestamp(rowSet.getTimestamp("timestamp"));
        return leagueInvite;
    }

}
