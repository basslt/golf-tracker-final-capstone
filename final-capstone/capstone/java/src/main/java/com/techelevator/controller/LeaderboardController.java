package com.techelevator.controller;

import com.techelevator.dao.LeaderboardDao;
import com.techelevator.model.Leaderboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leaderboards")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class LeaderboardController {

    @Autowired
    private LeaderboardDao leaderboardDao;

    @GetMapping("/{leagueId}")
    public List<Leaderboard> getOrderedLeaderboard(@PathVariable("leagueId") int leagueId) {
        return leaderboardDao.getAllLeaderboardsOrdered(leagueId);
    }

//    @GetMapping("/{leaderboardId}")
//    public ResponseEntity<Leaderboard> getLeaderboardById(@PathVariable int leaderboardId) {
//        Leaderboard leaderboard = leaderboardDao.getById(leaderboardId);
//        if (leaderboard != null) {
//            return ResponseEntity.ok(leaderboard);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/league/{leagueId}")
//    public ResponseEntity<List<Leaderboard>> getLeaderboardsByLeagueId(@PathVariable int leagueId) {
//        List<Leaderboard> leaderboards = leaderboardDao.getByLeagueId(leagueId);
//        if (!leaderboards.isEmpty()) {
//            return ResponseEntity.ok(leaderboards);
//        } else {
//            return ResponseEntity.noContent().build();
//        }
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Leaderboard>> getLeaderboardsByUserId(@PathVariable int userId) {
//        List<Leaderboard> leaderboards = leaderboardDao.getByUserId(userId);
//        if (!leaderboards.isEmpty()) {
//            return ResponseEntity.ok(leaderboards);
//        } else {
//            return ResponseEntity.noContent().build();
//        }
//    }

    @PostMapping
    public ResponseEntity<Void> addLeaderboard(@RequestBody Leaderboard leaderboard) {
        leaderboardDao.add(leaderboard);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{leaderboardId}")
    public ResponseEntity<Void> updateLeaderboard(@PathVariable int leaderboardId, @RequestBody Leaderboard leaderboard) {
        leaderboard.setLeaderboardId(leaderboardId);
        leaderboardDao.update(leaderboard);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{leaderboardId}")
    public ResponseEntity<Void> deleteLeaderboard(@PathVariable int leaderboardId) {
        leaderboardDao.delete(leaderboardId);
        return ResponseEntity.noContent().build();
    }
}
