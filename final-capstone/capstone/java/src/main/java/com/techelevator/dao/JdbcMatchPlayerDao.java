package com.techelevator.dao;

import com.techelevator.model.MatchPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public MatchPlayer findById(int matchPlayerId) throws ChangeSetPersister.NotFoundException {
        try {
            String query = "SELECT * FROM MatchPlayer WHERE match_player_id = ?";
            return jdbcTemplate.queryForObject(query, new MatchPlayerRowMapper(), matchPlayerId);
        } catch (EmptyResultDataAccessException e) {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<MatchPlayer> getAllMatchPlayers() {
        try {
            String query = "SELECT * FROM MatchPlayer";
            return jdbcTemplate.query(query, new MatchPlayerRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<MatchPlayer> findMatchPlayersByMatch(int matchId) {
        try {
            String query = "SELECT * FROM MatchPlayer WHERE match_id = ?";
            return jdbcTemplate.query(query, new MatchPlayerRowMapper(), matchId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<MatchPlayer> findMatchPlayersByPlayer(int playerId) {
        try {
            String query = "SELECT * FROM MatchPlayer WHERE player_id = ?";
            return jdbcTemplate.query(query, new MatchPlayerRowMapper(), playerId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void saveMatchPlayer(MatchPlayer matchPlayer) {
        try {
            String query = "INSERT INTO MatchPlayer (match_player_id, match_id, player_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(query, matchPlayer.getMatchPlayerId(), matchPlayer.getMatchId(), matchPlayer.getPlayerId());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to save match player: " + e.getMessage()) {
            };
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
        try {
            String query = "DELETE FROM MatchPlayer WHERE match_player_id = ?";
            jdbcTemplate.update(query, matchPlayerId);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to delete match player: " + e.getMessage()) {
            };
        }
    }

    private static class MatchPlayerRowMapper implements RowMapper<MatchPlayer> {
        @Override
        public MatchPlayer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int matchPlayerId = resultSet.getInt("match_player_id");
            int matchId = resultSet.getInt("match_id");
            int playerId = resultSet.getInt("player_id");
            return new MatchPlayer(matchPlayerId, matchId, playerId);
        }
    }
}
