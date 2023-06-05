package com.techelevator.dao;

import com.techelevator.model.TeeTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public void save(TeeTime teeTime) {
        String query = "INSERT INTO TeeTime (match_name, course_id, time, organizer_id, league_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, teeTime.getMatchName(), teeTime.getCourseId(), teeTime.getTime(),
                teeTime.getOrganizerId(), teeTime.getLeagueId());
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

