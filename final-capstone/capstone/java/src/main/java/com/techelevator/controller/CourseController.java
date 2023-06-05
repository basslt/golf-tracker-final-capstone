package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseDao courseDao;

    @Autowired
    public CourseController(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) throws ChangeSetPersister.NotFoundException {
        Course course = courseDao.findById(courseId);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


@GetMapping
    public List<Course> getCoursesByFilters(@RequestParam Map<String, String> filters) {
        String name = filters.get("name");
        String state = filters.get("state");
        String city = filters.get("city");

    // Convert filter values to lowercase
    if (name != null) {
        name = name.toLowerCase();
    }
    if (state != null) {
        state = state.toLowerCase();
    }
    if (city != null) {
        city = city.toLowerCase();
    }

    return courseDao.findCoursesByFilters(name, state, city);
}

    @GetMapping("/state/{state}")
    public List<Course> getCoursesByState(@PathVariable String state) {
        return courseDao.findByState(state);
    }

    @GetMapping("/city/{city}")
    public List<Course> getCoursesByCity(@PathVariable String city) {
        return courseDao.findByCity(city);
    }

    @GetMapping("/name/{name}")
    public List<Course> getCoursesByName(@PathVariable String name) {
        return courseDao.findByName(name);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        courseDao.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable int courseId, @RequestBody Course course) throws ChangeSetPersister.NotFoundException {
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
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId) throws ChangeSetPersister.NotFoundException {
        Course existingCourse = courseDao.findById(courseId);
        if (existingCourse != null) {
            courseDao.deleteCourse(courseId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
