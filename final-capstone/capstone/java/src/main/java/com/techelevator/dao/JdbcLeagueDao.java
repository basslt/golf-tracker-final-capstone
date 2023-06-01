package com.techelevator.dao;

import com.techelevator.model.League;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLeagueDao implements LeagueDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcLeagueDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<League> getAllLeagues() {
        List<League> leagues = new ArrayList<>();
        String sql = "SELECT * FROM League";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                League league =  mapRowToLeague(results);
                leagues.add(league);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return leagues;
    }

    @Override
    public League findLeagueById(int leagueId) {
        League league = null;
        String sql = "SELECT * FROM League WHERE league_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, leagueId);
            if (result.next()) {
                league = mapRowToLeague(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return league;
    }

    @Override
    public League findLeagueByName(String leagueName) {
        League league = null;
        String sql = "SELECT * FROM League WHERE name = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, leagueName);
            if (result.next()) {
                league = mapRowToLeague(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return league;
    }

    @Override
    public void createLeague(League league) {
        String query = "INSERT INTO League (name, organizer_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(query, league.getLeagueName(), league.getOrganizerId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public League updateLeague(League league, int leagueId) {
        League updatedLeague = null;
        String sql = "UPDATE League SET name = ?, organizer_id = ? WHERE league_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, league.getLeagueName(), league.getOrganizerId(), league.getLeagueId());
            if (numberOfRows == 0) {
                throw new RuntimeException("Zero rows affected, expected at least one");
            } else {
                updatedLeague = findLeagueById(league.getLeagueId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return updatedLeague;
    }

    @Override
    public void deleteLeague(int leagueId) {
        String sql = "DELETE FROM League WHERE league_id = ?;";
        try {
            jdbcTemplate.update(sql, leagueId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
    }

    private League mapRowToLeague(SqlRowSet rowSet) {
        League league = new League();
        league.setLeagueId(rowSet.getInt("league_id"));
        league.setLeagueName(rowSet.getString("name"));
        league.setOrganizerId(rowSet.getInt("organizer_id"));
        return league;
    }
}
