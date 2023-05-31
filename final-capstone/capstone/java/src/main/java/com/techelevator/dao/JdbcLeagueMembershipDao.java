package com.techelevator.dao;

import com.techelevator.model.LeagueMembership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLeagueMembershipDao implements LeagueMembershipDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcLeagueMembershipDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LeagueMembership findLeagueMembershipById(int leagueMembershipId) {
        LeagueMembership leagueMembership = null;
        String sql = "SELECT * FROM LeagueMembership WHERE league_membership_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, leagueMembershipId);
            if (result.next()) {
                leagueMembership = mapRowToLeagueMembership(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return leagueMembership;
    }

    @Override
    public List<LeagueMembership> getAllLeagueMemberships() {
        List<LeagueMembership> leagueMemberships = new ArrayList<>();
        String sql = "SELECT * FROM LeagueMembership;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                LeagueMembership leagueMembership = mapRowToLeagueMembership(results);
                leagueMemberships.add(leagueMembership);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return leagueMemberships;
    }

    @Override
    public List<LeagueMembership> findLeagueMembershipByLeagueId(int leagueId) {
        List<LeagueMembership> leagueMemberships = new ArrayList<>();
        String sql = "SELECT * FROM LeagueMembership WHERE league_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueId);
            while (results.next()) {
                LeagueMembership leagueMembership = mapRowToLeagueMembership(results);
                leagueMemberships.add(leagueMembership);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return leagueMemberships;
    }

    @Override
    public List<LeagueMembership> findLeagueMembershipByUserId(int userId) {
        List<LeagueMembership> leagueMemberships = new ArrayList<>();
        String sql = "SELECT * FROM LeagueMembership WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                LeagueMembership leagueMembership = mapRowToLeagueMembership(results);
                leagueMemberships.add(leagueMembership);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return leagueMemberships;
    }

    @Override
    public LeagueMembership addLeagueMembership(LeagueMembership leagueMembership) {
        LeagueMembership newLeagueMembership = null;
        String sql = "INSERT INTO LeagueMembership (league_id, user_id) VALUES (?, ?) RETURNING league_membership_id;";
        try {
            int newLeagueMembershipId = jdbcTemplate.queryForObject(sql, Integer.class, leagueMembership.getLeagueId(), leagueMembership.getUserId());
            newLeagueMembership = findLeagueMembershipById(newLeagueMembershipId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return newLeagueMembership;
    }

    @Override
    public LeagueMembership updateLeagueMembership(LeagueMembership leagueMembership, int id) {
        LeagueMembership updatedLeagueMembership = null;
        String sql = "UPDATE LeagueMembership SET league_id = ?, user_id = ? WHERE league_membership_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, leagueMembership.getLeagueId(), leagueMembership.getUserId(), leagueMembership.getLeagueMembershipId());
            if (numberOfRows == 0) {
                throw new RuntimeException("Zero rows affected, expected at least one");
            } else {
                updatedLeagueMembership = findLeagueMembershipById(leagueMembership.getLeagueMembershipId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return updatedLeagueMembership;
    }

    @Override
    public void deleteLeagueMembership(int leagueMembershipId) {
        String sql = "DELETE FROM LeagueMembership WHERE league_membership_id = ?;";
        try {
            jdbcTemplate.update(sql, leagueMembershipId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
    }


    private LeagueMembership mapRowToLeagueMembership(SqlRowSet rowSet) {
        LeagueMembership leagueMembership = new LeagueMembership();
        leagueMembership.setLeagueMembershipId(rowSet.getInt("league_membership_id"));
        leagueMembership.setLeagueId(rowSet.getInt("league_id"));
        leagueMembership.setUserId(rowSet.getInt("user_id"));
        return leagueMembership;
    }
}
