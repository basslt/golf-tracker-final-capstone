package com.techelevator.dao;

import com.sun.el.util.ReflectionUtil;
import com.techelevator.model.TeeTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
public class JdbcTeeTimeDao implements TeeTimeDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTeeTimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TeeTime findById(int teeTimeId) {
        String query = "SELECT * FROM TeeTime WHERE tee_time_id = ?";
        return jdbcTemplate.queryForObject(query, new TeeTimeRowMapper(), teeTimeId);
    }

    @Override
    public List<TeeTime> findAll() {
        String query = "SELECT * FROM TeeTime";
        return jdbcTemplate.query(query, new TeeTimeRowMapper());
    }

    @Override
    public TeeTime save(TeeTime teeTime) {
        TeeTime teeTime1 = null;
        String query = "INSERT INTO TeeTime (match_name, course_id, time, organizer_id, league_id) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING tee_time_id";
        try{
        int newTeeTimeId = jdbcTemplate.queryForObject(query, int.class, teeTime.getMatchName(),  teeTime.getCourseId(), teeTime.getTime(),  teeTime.getOrganizerId(),  teeTime.getLeagueId());
            System.out.println("New Tee Time ID: " + newTeeTimeId);
        teeTime1 = findById(newTeeTimeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return teeTime1;

    }

    @Override
    public void update(TeeTime teeTime) {
        String query = "UPDATE TeeTime SET match_name = ?, course_id = ?, time = ?, organizer_id = ?, league_id = ? " +
                "WHERE tee_time_id = ?";
        jdbcTemplate.update(query, teeTime.getMatchName(), teeTime.getCourseId(), teeTime.getTime(),
                teeTime.getOrganizerId(), teeTime.getLeagueId(), teeTime.getTeeTimeId());
    }

    @Override
    public void delete(TeeTime teeTime) {
        String query = "DELETE FROM TeeTime WHERE tee_time_id = ?";
        jdbcTemplate.update(query, teeTime.getTeeTimeId());
    }

    @Override
    public List<TeeTime> findByCourse(int courseId) {
        String query = "SELECT * FROM TeeTime WHERE course_id = ?";
        return jdbcTemplate.query(query, new TeeTimeRowMapper(), courseId);
    }

    private static class TeeTimeRowMapper implements RowMapper<TeeTime> {
        @Override
        public TeeTime mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int teeTimeId = resultSet.getInt("tee_time_id");
            String matchName = resultSet.getString("match_name");
            int courseId = resultSet.getInt("course_id");
            Timestamp time = resultSet.getTimestamp("time");
            int organizerId = resultSet.getInt("organizer_id");
            int leagueId = resultSet.getInt("league_id");
            return new TeeTime(teeTimeId, matchName, courseId, time, organizerId, leagueId);
        }
    }
}

