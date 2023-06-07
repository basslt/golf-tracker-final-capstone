package com.techelevator.dao;

import com.techelevator.model.Leaderboard;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLeaderboardDao implements LeaderboardDao {
    private final JdbcTemplate jdbcTemplate;


    public JdbcLeaderboardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Leaderboard> getAllLeaderboardsOrdered(int leagueId) {
        List<Leaderboard> leaderboards = new ArrayList<>();
        String sql = "SELECT * FROM Leaderboard WHERE league_id = ? ORDER BY total_score;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueId);
            while (results.next()) {
                Leaderboard leaderboard = mapRowToLeaderboard(results);
                leaderboards.add(leaderboard);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new RuntimeException("SQL syntax error", e);
        }
        return leaderboards;
        }


//    @Override
//    public Leaderboard getById(int leaderboardId) {
//        String query = "SELECT * FROM Leaderboard WHERE leaderboard_id = ?";
//        try {
//            return jdbcTemplate.queryForObject(query, new LeaderboardRowMapper(), leaderboardId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public List<Leaderboard> getByLeagueId(int leagueId) {
//        String query = "SELECT * FROM Leaderboard WHERE league_id = ?";
//        try {
//            return jdbcTemplate.query(query, new LeaderboardRowMapper(), leagueId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public List<Leaderboard> getByUserId(int userId) {
//        String query = "SELECT * FROM Leaderboard WHERE user_id = ?";
//        try {
//            return jdbcTemplate.query(query, new LeaderboardRowMapper(), userId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }



    @Override
    public void add(Leaderboard leaderboard) {
        String query = "INSERT INTO Leaderboard (league_id, user_id, total_score) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(query, leaderboard.getLeagueId(), leaderboard.getUserId(), leaderboard.getTotalScore());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Leaderboard leaderboard) {
        String query = "UPDATE Leaderboard SET league_id = ?, user_id = ?, total_score = ? WHERE leaderboard_id = ?";
        try {
            jdbcTemplate.update(query, leaderboard.getLeagueId(), leaderboard.getUserId(), leaderboard.getTotalScore(), leaderboard.getLeaderboardId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int leaderboardId) {
        String query = "DELETE FROM Leaderboard WHERE leaderboard_id = ?";
        try {
            jdbcTemplate.update(query, leaderboardId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Leaderboard mapRowToLeaderboard(SqlRowSet rowSet) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setLeaderboardId(rowSet.getInt("leaderboard_id"));
        leaderboard.setLeagueId(rowSet.getInt("league_id"));
        leaderboard.setUserId(rowSet.getInt("user_id"));
        leaderboard.setTotalScore(rowSet.getInt("total_score"));
        return leaderboard;
    }

//    private static class LeaderboardRowMapper implements RowMapper<Leaderboard> {
//        @Override
//        public Leaderboard mapRow(ResultSet rs, int rowNum) throws SQLException {
//            int leaderboardId = rs.getInt("leaderboard_id");
//            int leagueId = rs.getInt("league_id");
//            int userId = rs.getInt("user_id");
//            int totalScore = rs.getInt("total_score");
//            return new Leaderboard(leaderboardId, leagueId, userId, totalScore);
//        }

}
