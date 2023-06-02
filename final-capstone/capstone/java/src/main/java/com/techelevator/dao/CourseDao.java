package com.techelevator.dao;

import com.techelevator.model.Course;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


public interface CourseDao {
    Course findById(int courseId) throws ChangeSetPersister.NotFoundException;
    List<Course> getAllCourses();
    List<Course> findByState(String state);
    List<Course> findByCity(String city);
    void saveCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(int courseId);
    List<Course> findCoursesByFilters(String name, String state, String city);
    List<Course> findByName(String name);
}
