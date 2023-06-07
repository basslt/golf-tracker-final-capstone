package com.techelevator.dao;

import com.techelevator.model.Score;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class JdbcScoreDao implements ScoreDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcScoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Score findLatestPlayerScore(int userId, int leagueId) {
        Score score = new Score();
        String sql = "SELECT score_id, match_id, player_id, score FROM Score " +
                "JOIN TeeTime ON Score.match_id = TeeTime.tee_time_id " +
                "WHERE score.player_id = ? AND TeeTime.league_id = ? " +
                "ORDER BY TeeTime.time DESC LIMIT 1;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId, leagueId);
            if (result.next()) {
                score = mapRowToScore(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return score;
    }

    @Override
    public Score findById(int scoreId) {
        Score score = null;
        String query = "SELECT * FROM Score WHERE score_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(query, scoreId);
            if (result.next()) {
                score = mapRowToScore(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return score;
    }

    @Override
    public List<Score> getAllScores() {
        List<Score> scores = new ArrayList<>();
        String query = "SELECT * FROM Score";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query);
            while (results.next()) {
                Score score = mapRowToScore(results);
                scores.add(score);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
            }
        return scores;
    }

    @Override
    public List<Score> getScoresByMatch(int matchId) {
        List<Score> scores = new ArrayList<>();
        String query = "SELECT * FROM Score WHERE match_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, matchId);
            while (results.next()) {
                Score score = mapRowToScore(results);
                scores.add(score);
            }
        }  catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return scores;
    }

    @Override
    public List<Score> getScoresByPlayer(int playerId) {
        List<Score> scores = new ArrayList<>();
        String query = "SELECT * FROM Score WHERE player_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, playerId);
            while (results.next()) {
                Score score = mapRowToScore(results);
                scores.add(score);
            }
        }  catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return scores;
    }

    @Override
    public Score saveScore(Score score) {
        Score newScore = null;
        String query = "INSERT INTO Score (match_id, player_id, score) VALUES (?, ?, ?) RETURNING score_id;";
        try {
            int newScoreId = jdbcTemplate.queryForObject(query, int.class, score.getMatchId(), score.getPlayerId(), score.getScore());
            newScore = findById(newScoreId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return newScore;
    }

    @Override
    public Score updateScore(Score score, int scoreId) {
        Score updatedScore = null;
        String query = "UPDATE Score SET match_id = ?, player_id = ?, score = ? WHERE score_id = ?";
        try {
            int numberOfRows = jdbcTemplate.update(query, score.getMatchId(), score.getPlayerId(), score.getScore());
            if (numberOfRows == 0) {
                throw new RuntimeException("Zero rows affected, expected at least one");
            } else {
                updatedScore = findById(score.getScoreId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return updatedScore;
    }

    @Override
    public void deleteScore(int scoreId) {
        String query = "DELETE FROM Score WHERE score_id = ?";
        try {
            jdbcTemplate.update(query, scoreId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
    }


    public Score mapRowToScore(SqlRowSet rowSet) {
        Score score = new Score();
        score.setScoreId(rowSet.getInt("score_id"));
        score.setMatchId(rowSet.getInt("match_id"));
        score.setPlayerId(rowSet.getInt("player_id"));
        score.setScore(rowSet.getInt("score"));
        return score;
    }

//    private static class ScoreRowMapper implements RowMapper<Score> {
//        @Override
//        public Score mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//            int scoreId = resultSet.getInt("score_id");
//            int matchId = resultSet.getInt("match_id");
//            int playerId = resultSet.getInt("player_id");
//            int score = resultSet.getInt("score");
//            return new Score(scoreId, matchId, playerId, score);
//        }
//    }
}


