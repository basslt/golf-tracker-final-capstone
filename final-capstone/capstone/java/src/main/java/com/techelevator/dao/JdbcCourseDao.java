package com.techelevator.dao;

import com.techelevator.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseDao implements CourseDao {
    private final Connection connection;

    public JdbcCourseDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Course findById(int courseId) {
        String query = "SELECT * FROM Course WHERE course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createCourseFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Course course = createCourseFromResultSet(resultSet);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public List<Course> findByState(String state) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course WHERE state = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, state);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course course = createCourseFromResultSet(resultSet);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public List<Course> findByCity(String city) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course WHERE city = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, city);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course course = createCourseFromResultSet(resultSet);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public void saveCourse(Course course) {
        String query = "INSERT INTO Course (name, address, city, state, zipcode, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getAddress());
            statement.setString(3, course.getCity());
            statement.setString(4, course.getState());
            statement.setInt(5, course.getZipCode());
            statement.setFloat(6, course.getLatitude());
            statement.setFloat(7, course.getLongitude());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourse(Course course) {
        String query = "UPDATE Course SET name = ?, address = ?, city = ?, state = ?, zipcode = ?, latitude = ?, longitude = ? WHERE course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getAddress());
            statement.setString(3, course.getCity());
            statement.setString(4, course.getState());
            statement.setInt(5, course.getZipCode());
            statement.setFloat(6, course.getLatitude());
            statement.setFloat(7, course.getLongitude());
            statement.setInt(8, course.getCourseId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        String query = "DELETE FROM Course WHERE course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Course createCourseFromResultSet(ResultSet resultSet) throws SQLException {
        int courseId = resultSet.getInt("course_id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String city = resultSet.getString("city");
        String state = resultSet.getString("state");
        int zipcode = resultSet.getInt("zipcode");
        float latitude = resultSet.getFloat("latitude");
        float longitude = resultSet.getFloat("longitude");
        return new Course(courseId, name, address, city, state, zipcode, latitude, longitude);
    }

}
