package com.techelevator.dao;

import com.techelevator.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class JdbcCourseDao implements CourseDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course findById(int courseId) throws ChangeSetPersister.NotFoundException {
        try {
            String query = "SELECT * FROM Course WHERE course_id = ?";
            return jdbcTemplate.queryForObject(query, new CourseRowMapper(), courseId);
        } catch (EmptyResultDataAccessException e) {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try {
            String query = "SELECT * FROM Course";
            return jdbcTemplate.query(query, new CourseRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Course> findByState(String state) {
        try {
            String query = "SELECT * FROM Course WHERE state = ?";
            return jdbcTemplate.query(query, new CourseRowMapper(), state);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Course> findByCity(String city) {
        try {
            String query = "SELECT * FROM Course WHERE city = ?";
            return jdbcTemplate.query(query, new CourseRowMapper(), city);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void saveCourse(Course course) {
        try {
            String query = "INSERT INTO Course (name, address, city, state, zipcode, latitude, longitude) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, course.getName(), course.getAddress(), course.getCity(), course.getState(),
                    course.getZipCode(), course.getLatitude(), course.getLongitude());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to save course: " + e.getMessage()) {
            };
        }
    }

    @Override
    public void updateCourse(Course course) {
        try {
            String query = "UPDATE Course SET name = ?, address = ?, city = ?, state = ?, zipcode = ?, " +
                    "latitude = ?, longitude = ? WHERE course_id = ?";
            jdbcTemplate.update(query, course.getName(), course.getAddress(), course.getCity(), course.getState(),
                    course.getZipCode(), course.getLatitude(), course.getLongitude(), course.getCourseId());
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to update course: " + e.getMessage()) {
            };
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try {
            String query = "DELETE FROM Course WHERE course_id = ?";
            jdbcTemplate.update(query, courseId);
        } catch (DataAccessException e) {
            throw new DataAccessException("Failed to delete course: " + e.getMessage()) {
            };
        }
    }

    private static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int courseId = resultSet.getInt("course_id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String state = resultSet.getString("state");
            int zipCode = resultSet.getInt("zipcode");
            float latitude = resultSet.getFloat("latitude");
            float longitude = resultSet.getFloat("longitude");
            return new Course(courseId, name, address, city, state, zipCode, latitude, longitude);
        }
    }
}
