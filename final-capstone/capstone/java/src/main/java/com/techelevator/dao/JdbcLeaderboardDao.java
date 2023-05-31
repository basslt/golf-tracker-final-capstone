package com.techelevator.dao;

import com.techelevator.model.Leaderboard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcLeaderboardDao implements LeaderboardDao {
    private JdbcTemplate jdbcTemplate;

    // Constructor
    public JdbcLeaderboardDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Leaderboard getById(int leaderboardId) {
        String query = "SELECT * FROM Leaderboard WHERE leaderboard_id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new LeaderboardRowMapper(), leaderboardId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Leaderboard> getByLeagueId(int leagueId) {
        String query = "SELECT * FROM Leaderboard WHERE league_id = ?";
        try {
            return jdbcTemplate.query(query, new LeaderboardRowMapper(), leagueId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Leaderboard> getByUserId(int userId) {
        String query = "SELECT * FROM Leaderboard WHERE user_id = ?";
        try {
            return jdbcTemplate.query(query, new LeaderboardRowMapper(), userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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

    private static class LeaderboardRowMapper implements RowMapper<Leaderboard> {
        @Override
        public Leaderboard mapRow(ResultSet rs, int rowNum) throws SQLException {
            int leaderboardId = rs.getInt("leaderboard_id");
            int leagueId = rs.getInt("league_id");
            int userId = rs.getInt("user_id");
            int totalScore = rs.getInt("total_score");
            return new Leaderboard(leaderboardId, leagueId, userId, totalScore);
        }
    }
}
