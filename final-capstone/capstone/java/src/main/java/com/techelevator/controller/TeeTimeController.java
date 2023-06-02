package com.techelevator.controller;

import com.techelevator.dao.TeeTimeDao;
import com.techelevator.model.TeeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@PreAuthorize("isAuthenticated()")
//@CrossOrigin
//@RestController
//public class TeeTimeController {
//    private final TeeTimeDao teeTimeDao;
//
//    @Autowired
//    public TeeTimeController(TeeTimeDao teeTimeDao) {
//        this.teeTimeDao = teeTimeDao;
//    }
//
//    @GetMapping("/{teeTimeId}")
//    public ResponseEntity<TeeTime> getTeeTimeById(@PathVariable int teeTimeId) throws ChangeSetPersister.NotFoundException {
//        TeeTime teeTime = teeTimeDao.findById(teeTimeId);
//        if (teeTime != null) {
//            return ResponseEntity.ok(teeTime);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping
//    public List<TeeTime> getAllTeeTimes() {
//        return teeTimeDao.getAllTeeTimes();
//    }
//
//    @GetMapping(params = "courseId")
//    public List<TeeTime> getTeeTimesByCourse(@RequestParam int courseId) {
//        return teeTimeDao.getTeeTimesByCourse(courseId);
//    }
//
//    @GetMapping(params = "playerId")
//    public List<TeeTime> getTeeTimesByPlayer(@RequestParam int playerId) {
//        return teeTimeDao.getTeeTimesByPlayer(playerId);
//    }
//
//    @PostMapping
//    public ResponseEntity<TeeTime> addTeeTime(@RequestBody TeeTime teeTime) {
//        teeTimeDao.saveTeeTime(teeTime);
//        return ResponseEntity.status(HttpStatus.CREATED).body(teeTime);
//    }
//
//    @PutMapping("/{teeTimeId}")
//    public ResponseEntity<TeeTime> updateTeeTime(@PathVariable int teeTimeId, @RequestBody TeeTime teeTime) throws ChangeSetPersister.NotFoundException {
//        TeeTime existingTeeTime = teeTimeDao.findById(teeTimeId);
//        if (existingTeeTime != null) {
//            teeTime.setTeeTimeId(teeTimeId);
//            teeTimeDao.updateTeeTime(teeTime);
//            return ResponseEntity.ok(teeTime);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{teeTimeId}")
//    public ResponseEntity<Void> deleteTeeTime(@PathVariable int teeTimeId) throws ChangeSetPersister.NotFoundException {
//        TeeTime existingTeeTime = teeTimeDao.findById(teeTimeId);
//        if (existingTeeTime != null) {
//            teeTimeDao.deleteTeeTime(teeTimeId);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}