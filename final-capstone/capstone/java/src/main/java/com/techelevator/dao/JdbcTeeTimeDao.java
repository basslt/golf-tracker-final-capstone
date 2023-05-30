package com.techelevator.dao;

import com.techelevator.model.TeeTime;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcTeeTimeDAO implements TeeTimeDao {
    private final Connection connection;

    public JdbcTeeTimeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TeeTime findById(int teeTimeId) {
        String query = "SELECT * FROM TeeTime WHERE tee_time_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teeTimeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createTeeTimeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TeeTime> getAllTeeTimes() {
        List<TeeTime> teeTimes = new ArrayList<>();
        String query = "SELECT * FROM TeeTime";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                TeeTime teeTime = createTeeTimeFromResultSet(resultSet);
                teeTimes.add(teeTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teeTimes;
    }

    @Override
    public List<TeeTime> getTeeTimesByCourse(int courseId) {
        List<TeeTime> teeTimes = new ArrayList<>();
        String query = "SELECT * FROM TeeTime WHERE course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TeeTime teeTime = createTeeTimeFromResultSet(resultSet);
                teeTimes.add(teeTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teeTimes;
    }

    @Override
    public List<TeeTime> getTeeTimesByPlayer(int playerId) {
        List<TeeTime> teeTimes = new ArrayList<>();
        String query = "SELECT * FROM TeeTime WHERE ? = ANY (player_ids)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TeeTime teeTime = createTeeTimeFromResultSet(resultSet);
                teeTimes.add(teeTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teeTimes;
    }

    @Override
    public void saveTeeTime(TeeTime teeTime) {
        String query = "INSERT INTO TeeTime (date_time, course_id, player_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, Timestamp.valueOf(teeTime.getDateTime()));
            statement.setInt(2, teeTime.getCourseId());
            statement.setArray(3, connection.createArrayOf("integer", teeTime.getPlayerId().toArray()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTeeTime(TeeTime teeTime) {
        String query = "UPDATE TeeTime SET date_time = ?, course_id = ?, player_ids = ? WHERE tee_time_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, Timestamp.valueOf(teeTime.getDateTime()));
            statement.setInt(2, teeTime.getCourseId());
            statement.setArray(3, connection.createArrayOf("integer", teeTime.getPlayerId().toArray()));
            statement.setInt(4, teeTime.getTeeTimeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeeTime(int teeTimeId) {
        String query = "DELETE FROM TeeTime WHERE tee_time_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teeTimeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TeeTime createTeeTimeFromResultSet(ResultSet resultSet) throws SQLException {
        int teeTimeId = resultSet.getInt("tee_time_id");
        LocalDateTime dateTime = resultSet.getTimestamp("date_time").toLocalDateTime();
        int courseId = resultSet.getInt("course_id");
        Array playerIdsArray = resultSet.getArray("player_id");
        int organizerId = resultSet.getInt("organizer_id");
        Integer[] playerIds = (Integer[]) playerIdsArray.getArray();
        List<Integer> playerId = new ArrayList<>(Arrays.asList(playerIds));
        return new TeeTime(teeTimeId, dateTime, courseId, playerId, organizerId);
    }
}