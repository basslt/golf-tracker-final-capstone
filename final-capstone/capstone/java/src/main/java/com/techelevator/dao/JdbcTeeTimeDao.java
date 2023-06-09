package com.techelevator.dao;

import com.techelevator.model.TeeTime;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTeeTimeDao implements TeeTimeDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTeeTimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TeeTime findById(int teeTimeId) {
        TeeTime teeTime = null;
        String query = "SELECT * FROM TeeTime WHERE tee_time_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, teeTimeId);
            if (results.next()) {
                teeTime= mapRowToTeeTime(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
            return teeTime;
    }

    @Override
    public List<TeeTime> getUpcomingTeeTimesByLeague(int leagueId) {
        List<TeeTime> upcomingTeeTimes = new ArrayList<>();
        String sql = "SELECT * FROM TeeTime WHERE league_id = ? AND time > NOW();";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueId);
            while (results.next()) {
                TeeTime teeTime = mapRowToTeeTime(results);
                upcomingTeeTimes.add(teeTime);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return upcomingTeeTimes;
    }

    @Override
    public List<TeeTime> getPastTeeTimeByLeague(int leagueId) {
        List<TeeTime> pastTeeTimes = new ArrayList<>();
        String sql = "SELECT * FROM TeeTime WHERE league_id = ? AND time < NOW();";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueId);
            while (results.next()) {
                TeeTime teeTime = mapRowToTeeTime(results);
                pastTeeTimes.add(teeTime);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return pastTeeTimes;
    }


    @Override
    public List<TeeTime> findAll() {
        List<TeeTime> teeTimes = new ArrayList<>();
        String query = "SELECT * FROM TeeTime";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query);
            while (results.next()) {
                TeeTime teeTime = mapRowToTeeTime(results);
                teeTimes.add(teeTime);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return teeTimes;
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
    public int getLeagueIdByTeeTimeId(int teeTimeId) {
        String sql = "SELECT league_id FROM TeeTime WHERE tee_time_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, teeTimeId);
    }

    @Override
    public List<TeeTime> findByCourse(int courseId) {
        return null;
    }

    //    @Override
//    public List<TeeTime> findByCourse(int courseId) {
//        String query = "SELECT * FROM TeeTime WHERE course_id = ?";
//        return jdbcTemplate.query(query, new TeeTimeRowMapper(), courseId);
//    }
    @Override
    public int getLeagueIdByUsername(String username) {
        String sql = "SELECT t.league_id " +
                "FROM TeeTime t " +
                "JOIN MatchPlayer mp ON t.match_id = mp.match_id " +
                "JOIN users u ON mp.player_id = u.user_id " +
                "WHERE u.username = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, username);
    }

        @Override
    public int getTeeTimeIdByMatchId(int matchId) {
        String sql = "SELECT tee_time_id FROM TeeTime WHERE match_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, matchId);
    }


        public TeeTime mapRowToTeeTime(SqlRowSet rowSet)  {
            TeeTime teeTime = new TeeTime();
            teeTime.setTeeTimeId(rowSet.getInt("tee_time_id"));
            teeTime.setMatchName(rowSet.getString("match_name"));
            teeTime.setCourseId(rowSet.getInt("course_id"));
            teeTime.setTime(rowSet.getTimestamp("time"));
            teeTime.setOrganizerId(rowSet.getInt("organizer_id"));
            teeTime.setLeagueId(rowSet.getInt("league_id"));
            return teeTime;
        }
    }


