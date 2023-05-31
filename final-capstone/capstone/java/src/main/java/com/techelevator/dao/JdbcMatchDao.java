package com.techelevator.dao;

import com.techelevator.model.Match;
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
public class JdbcMatchDao implements MatchDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMatchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Match findById(int matchId) throws ChangeSetPersister.NotFoundException {
        try {
            String query = "SELECT * FROM Match WHERE match_id = ?";
            return jdbcTemplate.queryForObject(query, new MatchRowMapper(), matchId);
        } catch (EmptyResultDataAccessException e) {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<Match> getAllMatches() {
        try {
            String query = "SELECT * FROM Match";
            return jdbcTemplate.query(query, new MatchRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Match> getMatchesByLeague(int leagueId) {
        try {
            String query = "SELECT * FROM Match WHERE league_id = ?";
            return jdbcTemplate.query(query, new MatchRowMapper(), leagueId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void saveMatch(Match match) {
        try {
            String query = "INSERT INTO Match (match_name, league_id, tee_time_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(query, match.getMatchName(), match.getLeagueId(), match.getTeeTimeId());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to save match: " + e.getMessage()) {
            };
        }
    }

    @Override
    public void updateMatch(Match match) {
        try {
            String query = "UPDATE Match SET match_name = ?, league_id = ?, tee_time_id = ? WHERE match_id = ?";
            jdbcTemplate.update(query, match.getMatchName(), match.getLeagueId(), match.getTeeTimeId(), match.getMatchId());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to update match: " + e.getMessage()) {
            };
        }
    }

    @Override
    public void deleteMatch(int matchId) {
        try {
            String query = "DELETE FROM Match WHERE match_id = ?";
            jdbcTemplate.update(query, matchId);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to delete match: " + e.getMessage()) {
            };
        }
    }

    private static class MatchRowMapper implements RowMapper<Match> {
        @Override
        public Match mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int matchId = resultSet.getInt("match_id");
            String matchName = resultSet.getString("match_name");
            int leagueId = resultSet.getInt("league_id");
            int teeTimeId = resultSet.getInt("tee_time_id");
            return new Match(matchId, matchName, leagueId, teeTimeId);
        }
    }
}
