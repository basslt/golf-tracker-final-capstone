package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseDao courseDao;

    @Autowired
    public CourseController(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {
        Course course = courseDao.findById(courseId);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @GetMapping(params = "state")
    public List<Course> getCoursesByState(@RequestParam String state) {
        return courseDao.findByState(state);
    }

    @GetMapping(params = "city")
    public List<Course> getCoursesByCity(@RequestParam String city) {
        return courseDao.findByCity(city);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        courseDao.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable int courseId, @RequestBody Course course) {
        Course existingCourse = courseDao.findById(courseId);
        if (existingCourse != null) {
            course.setCourseId(courseId);
            courseDao.updateCourse(course);
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId) {
        Course existingCourse = courseDao.findById(courseId);
        if (existingCourse != null) {
            courseDao.deleteCourse(courseId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

