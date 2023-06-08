package com.techelevator.controller;

import com.techelevator.dao.ScoreDao;
import com.techelevator.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@CrossOrigin
@RestController
public class ScoreController {

    @Autowired
    private ScoreDao scoreDao;

    @GetMapping("/scores/{leagueId}/most-recent/{userId}")
    public Score getMostRecentPlayerScore(@PathVariable("userId") int userId, @PathVariable("leagueId") int leagueId) {
        Score score = scoreDao.findLatestPlayerScore(userId, leagueId);
        return score;
    }

    @GetMapping("/scores/{id}")
    public Score getScoreById(@PathVariable("id") int scoreId) {
        Score score = scoreDao.findById(scoreId);
        if (score == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Score not found.");
        }
        return score;
    }

    @GetMapping("/scores")
    public ResponseEntity<List<Score>> getAllScores() {
        List<Score> scores = scoreDao.getAllScores();
        return ResponseEntity.ok(scores);
    }

    @GetMapping("/scores/matches/{matchId}")
    public ResponseEntity<List<Score>> getScoresByMatch(@PathVariable("matchId") int matchId) {
        List<Score> scores = scoreDao.getScoresByMatch(matchId);
        return ResponseEntity.ok(scores);
    }

    @GetMapping("/scores/players/{playerId}")
    public ResponseEntity<List<Score>> getScoresByPlayer(@PathVariable("playerId") int playerId) {
        List<Score> scores = scoreDao.getScoresByPlayer(playerId);
        return ResponseEntity.ok(scores);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/scores")
    public Score createScore(@RequestBody Score score) {
        return scoreDao.saveScore(score);
    }

    @PutMapping("/scores/{id}")
    public ResponseEntity<Score> updateScore(@PathVariable("id") int scoreId, @RequestBody Score score) {
        Score updatedScore = scoreDao.updateScore(score, score.getScoreId());
        return ResponseEntity.ok(updatedScore);
    }

    @DeleteMapping("/scores/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable("id") int scoreId) {
        scoreDao.deleteScore(scoreId);
        return ResponseEntity.noContent().build();
    }

//    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
//    public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
}
