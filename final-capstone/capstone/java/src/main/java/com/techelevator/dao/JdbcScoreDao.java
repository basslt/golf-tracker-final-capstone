package com.techelevator.dao;

import com.techelevator.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcScoreDao implements ScoreDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcScoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Score findById(int scoreId) throws ChangeSetPersister.NotFoundException {
        try {
            String query = "SELECT * FROM Score WHERE score_id = ?";
            return jdbcTemplate.queryForObject(query, new ScoreRowMapper(), scoreId);
        } catch (EmptyResultDataAccessException e) {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<Score> getAllScores() {
        try {
            String query = "SELECT * FROM Score";
            return jdbcTemplate.query(query, new ScoreRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Score> getScoresByMatch(int matchId) {
        try {
            String query = "SELECT * FROM Score WHERE match_id = ?";
            return jdbcTemplate.query(query, new ScoreRowMapper(), matchId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Score> getScoresByPlayer(int playerId) {
        try {
            String query = "SELECT * FROM Score WHERE player_id = ?";
            return jdbcTemplate.query(query, new ScoreRowMapper(), playerId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void saveScore(Score score) {
        try {
            String query = "INSERT INTO Score (match_id, player_id, score) VALUES (?, ?, ?)";
            jdbcTemplate.update(query, score.getMatchId(), score.getPlayerId(), score.getScore());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to save score: " + e.getMessage()) {
            };
        }
    }

    @Override
    public void updateScore(Score score) {
        try {
            String query = "UPDATE Score SET match_id = ?, player_id = ?, score = ? WHERE score_id = ?";
            jdbcTemplate.update(query, score.getMatchId(), score.getPlayerId(), score.getScore(), score.getScoreId());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to update score: " + e.getMessage()) {
            };
        }
    }

    @Override
    public void deleteScore(int scoreId) {
        try {
            String query = "DELETE FROM Score WHERE score_id = ?";
            jdbcTemplate.update(query, scoreId);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to delete score: " + e.getMessage()) {
            };
        }
    }

    private static class ScoreRowMapper implements RowMapper<Score> {
        @Override
        public Score mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int scoreId = resultSet.getInt("score_id");
            int matchId = resultSet.getInt("match_id");
            int playerId = resultSet.getInt("player_id");
            int score = resultSet.getInt("score");
            return new Score(scoreId, matchId, playerId, score);
        }
    }
}


