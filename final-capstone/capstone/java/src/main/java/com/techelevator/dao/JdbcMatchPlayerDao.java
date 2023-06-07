package com.techelevator.dao;

import com.techelevator.model.MatchPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class JdbcMatchPlayerDao implements MatchPlayerDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMatchPlayerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MatchPlayer findById(int matchPlayerId) {
        MatchPlayer matchPlayer = null;
        String query = "SELECT * FROM MatchPlayer WHERE match_player_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, matchPlayerId);
            if (results.next()) {
                matchPlayer = mapRowToMatchPlayer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return matchPlayer;
    }

    @Override
    public List<MatchPlayer> getAllMatchPlayers() {
        return null;
    }

    @Override
    public List<MatchPlayer> findMatchPlayersByMatch(int matchId) {
        return null;
    }

    @Override
    public List<MatchPlayer> findMatchPlayersByPlayer(int playerId) {
        return null;
    }
//
//    @Override
//    public List<MatchPlayer> getAllMatchPlayers() {
//        try {
//            String query = "SELECT * FROM MatchPlayer";
//            return jdbcTemplate.query(query, new MatchPlayerRowMapper());
//        } catch (EmptyResultDataAccessException e) {
//            return Collections.emptyList();
//        }
//    }
//
//    @Override
//    public List<MatchPlayer> findMatchPlayersByMatch(int matchId) {
//        try {
//            String query = "SELECT * FROM MatchPlayer WHERE match_id = ?";
//            return jdbcTemplate.query(query, new MatchPlayerRowMapper(), matchId);
//        } catch (EmptyResultDataAccessException e) {
//            return Collections.emptyList();
//        }
//    }

//    @Override
//    public List<MatchPlayer> findMatchPlayersByPlayer(int playerId) {
//        try {
//            String query = "SELECT * FROM MatchPlayer WHERE player_id = ?";
//            return jdbcTemplate.query(query, new MatchPlayerRowMapper(), playerId);
//        } catch (EmptyResultDataAccessException e) {
//            return Collections.emptyList();
//        }
//    }

    public int getMatchIdByPlayerId(int playerId) {
        String query = "SELECT *  FROM MatchPlayer WHERE player_id = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, playerId);
    }

    @Override
    public MatchPlayer saveMatchPlayer(MatchPlayer matchPlayer) {
        try {
            String query = "INSERT INTO MatchPlayer (match_id, player_id) VALUES ( ?, ?) RETURNING match_player_id";
            int newMatchPlayerId = jdbcTemplate.queryForObject(query, int.class, matchPlayer.getMatchId(), matchPlayer.getPlayerId());
            MatchPlayer newMatchPlayer = findById(newMatchPlayerId);
            return newMatchPlayer;
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
    }


    @Override
    public void updateMatchPlayer(MatchPlayer matchPlayer) {
        try {
            String query = "UPDATE MatchPlayer SET match_id = ?, player_id = ? WHERE match_player_id = ?";
            jdbcTemplate.update(query, matchPlayer.getMatchId(), matchPlayer.getPlayerId(), matchPlayer.getMatchPlayerId());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to update match player: " + e.getMessage()) {
            };
        }
    }

    @Override
    public void deleteMatchPlayer(int matchPlayerId) {
        String query = "DELETE FROM MatchPlayer WHERE match_player_id = ?";
        jdbcTemplate.update(query, matchPlayerId);
    }


    @Override
    public MatchPlayer mapRowToMatchPlayer(SqlRowSet rowSet) {
        MatchPlayer matchPlayer = new MatchPlayer();
        matchPlayer.setMatchPlayerId(rowSet.getInt("match_player_id"));
        matchPlayer.setMatchId(rowSet.getInt("match_id"));
        matchPlayer.setPlayerId(rowSet.getInt("player_id"));
        return matchPlayer;

    }
}
