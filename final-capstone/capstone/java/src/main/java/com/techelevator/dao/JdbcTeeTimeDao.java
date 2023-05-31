package com.techelevator.dao;

import com.techelevator.model.TeeTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class JdbcTeeTimeDao implements TeeTimeDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTeeTimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate =  jdbcTemplate;
    }

    @Override
    public TeeTime findById(int teeTimeId) throws ChangeSetPersister.NotFoundException {
        try {
            String query = "SELECT * FROM TeeTime WHERE tee_time_id = ?";
            return jdbcTemplate.queryForObject(query, new TeeTimeRowMapper(), teeTimeId);
        } catch (EmptyResultDataAccessException e) {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<TeeTime> getAllTeeTimes() {
        try {
            String query = "SELECT * FROM TeeTime";
            return jdbcTemplate.query(query, new TeeTimeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<TeeTime> getTeeTimesByCourse(int courseId) {
        try {
            String query = "SELECT * FROM TeeTime WHERE course_id = ?";
            return jdbcTemplate.query(query, new TeeTimeRowMapper(), courseId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<TeeTime> getTeeTimesByPlayer(int playerId) {
        try {
            String query = "SELECT * FROM TeeTime WHERE ? = ANY(player_ids)";
            return jdbcTemplate.query(query, new TeeTimeRowMapper(), playerId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }


    @Override
    public void saveTeeTime(TeeTime teeTime) {
        try {
            String query = "INSERT INTO TeeTime (tee_time_id, date_time, course_id, player_ids, organizer_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, teeTime.getTeeTimeId(), teeTime.getDateTime(), teeTime.getCourseId(),
                    teeTime.getPlayerId(), teeTime.getOrganizerId());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to save tee time: " + e.getMessage()) {
            };
        }
    }

    @Override
    public void updateTeeTime(TeeTime teeTime) {
        try {
            String query = "UPDATE TeeTime SET date_time = ?, course_id = ?, player_ids = ?, organizer_id = ? " +
                    "WHERE tee_time_id = ?";
            jdbcTemplate.update(query, teeTime.getDateTime(), teeTime.getCourseId(), teeTime.getPlayerId(),
                    teeTime.getOrganizerId(), teeTime.getTeeTimeId());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to update tee time: " + e.getMessage()) {
            };
        }
    }

    @Override
    public void deleteTeeTime(int teeTimeId) {
        try {
            String query = "DELETE FROM TeeTime WHERE tee_time_id = ?";
            jdbcTemplate.update(query, teeTimeId);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to delete tee time: " + e.getMessage()) {
            };
        }
    }

    private static class TeeTimeRowMapper implements RowMapper<TeeTime> {
        @Override
        public TeeTime mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int teeTimeId = resultSet.getInt("tee_time_id");
            LocalDateTime dateTime = resultSet.getTimestamp("date_time").toLocalDateTime();
            int courseId = resultSet.getInt("course_id");
            List<Integer> playerIds = parsePlayerIds(resultSet.getString("player_ids"));
            int organizerId = resultSet.getInt("organizer_id");
            return new TeeTime(teeTimeId, dateTime, courseId, playerIds, organizerId);
        }

        private List<Integer> parsePlayerIds(String playerIdStr) {
            List<Integer> playerId = new ArrayList<>();
            if (playerIdStr != null && !playerIdStr.isEmpty()) {
                String[] ids = playerIdStr.split(",");
                for (String id : ids) {
                    playerId.add(Integer.parseInt(id));
                }
            }
            return playerId;
        }
    }
}
