package com.techelevator.dao;

import com.techelevator.model.Course;

import java.util.List;


public interface CourseDao {
    Course findById(int courseId);
    List<Course> getAllCourses();
    List<Course> findByState(String state);
    List<Course> findByCity(String city);
    void saveCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(int courseId);
}
