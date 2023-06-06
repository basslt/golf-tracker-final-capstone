package com.techelevator.dao;

import com.techelevator.model.Message;
import com.techelevator.model.TeeTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import javax.sql.RowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        TeeTime newTeeTime = null;
        String query = "SELECT * FROM TeeTime WHERE tee_time_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(query, teeTimeId);
            if (results.next()) {
                newTeeTime = mapRowToTeeTime(results);
            }}
            catch(CannotGetJdbcConnectionException e){
                throw new RuntimeException("Unable to connect to server or database", e);
            }
            return newTeeTime;
        }

    @Override
    public List<TeeTime> findAll() {
        List<TeeTime> teeTimes = new ArrayList<>();
        String query = " SELECT * FROM TeeTimes";
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

//            @Override
//            public List<TeeTime> findAll () {
//                String query = "SELECT * FROM TeeTime";
//                return jdbcTemplate.query(query, new TeeTimeRowMapper());
//            }
//

            @Override
            public TeeTime createTeeTime (TeeTime teeTime){
                TeeTime newTeeTime = null;
                String query = "INSERT INTO TeeTime (match_name, course_id, time, organizer_id, league_id) " +
                        "VALUES (?, ?, ?, ?, ?) RETURNING tee_time_id";
                try {
                    int newTeeTimeId = jdbcTemplate.queryForObject(query, int.class, teeTime.getMatchName(), teeTime.getCourseId(), teeTime.getTime(),
                            teeTime.getOrganizerId(), teeTime.getLeagueId());
                    newTeeTime = findById(newTeeTimeId);
                } catch (
                        CannotGetJdbcConnectionException e) {
                    throw new RuntimeException("Unable to connect to server or database", e);
                } catch (BadSqlGrammarException e) {
                    throw new RuntimeException("SQL syntax error", e);
                } catch (
                        DataIntegrityViolationException e) {
                    throw new RuntimeException("Data integrity violation", e);
                }
                return newTeeTime;
            }

            @Override
            public void update (TeeTime teeTime){
                String query = "UPDATE TeeTime SET match_name = ?, course_id = ?, time = ?, organizer_id = ?, league_id = ? " +
                        "WHERE tee_time_id = ?";
                jdbcTemplate.update(query, teeTime.getMatchName(), teeTime.getCourseId(), teeTime.getTime(),
                        teeTime.getOrganizerId(), teeTime.getLeagueId(), teeTime.getTeeTimeId());
            }

            @Override
            public void delete (TeeTime teeTime){
                String query = "DELETE FROM TeeTime WHERE tee_time_id = ?";
                jdbcTemplate.update(query, teeTime.getTeeTimeId());
            }
            @Override
            public int getLeagueIdByTeeTimeId ( int teeTimeId){
                String sql = "SELECT league_id FROM TeeTime WHERE tee_time_id = ?";
                return jdbcTemplate.queryForObject(sql, Integer.class, teeTimeId);
            }

    @Override
    public List<TeeTime> findByCourse(int courseId) {
        return null;
    }

    //            @Override
//            public List<TeeTime> findByCourse ( int courseId){
//                String query = "SELECT * FROM TeeTime WHERE course_id = ?";
//                return jdbcTemplate.query(query, new TeeTimeRowMapper(), courseId);
//            }
            @Override
            public int getLeagueIdByUsername (String username){
                String sql = "SELECT t.league_id " +
                        "FROM TeeTime t " +
                        "JOIN MatchPlayer mp ON t.match_id = mp.match_id " +
                        "JOIN users u ON mp.player_id = u.user_id " +
                        "WHERE u.username = ?";
                return jdbcTemplate.queryForObject(sql, Integer.class, username);
            }

            @Override
            public int getTeeTimeIdByMatchId ( int matchId){
                String sql = "SELECT tee_time_id FROM TeeTime WHERE match_id = ?";
                return jdbcTemplate.queryForObject(sql, Integer.class, matchId);
            }


            public TeeTime mapRowToTeeTime (SqlRowSet rowSet){
               TeeTime teeTime = new TeeTime();
                TeeTime.setTeeTimeId(rowSet.getInt("tee_time_id"));
                TeeTime.setMatchName(rowSet.getString("match_name"));
                TeeTime.setCourseId(rowSet.getInt("course_id"));
                TeeTime.setTime(rowSet.getTimestamp("time"));
                TeeTime.setOrganizerId(rowSet.getInt("organizer_id"));
                TeeTime.setLeagueId(rowSet.getInt("league_id"));
                return teeTime;
            }

        }
