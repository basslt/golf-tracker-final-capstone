package com.techelevator.controller;

import com.techelevator.dao.ScoreDao;
import com.techelevator.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {
    private final ScoreDao scoreDao;

    @Autowired
    public ScoreController(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    @GetMapping("/scores/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable("id") int scoreId) throws ChangeSetPersister.NotFoundException {
        Score score = scoreDao.findById(scoreId);
        if (score == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return ResponseEntity.ok(score);
    }

    @GetMapping("/scores")
    public ResponseEntity<List<Score>> getAllScores() {
        List<Score> scores = scoreDao.getAllScores();
        return ResponseEntity.ok(scores);
    }

    @GetMapping("/matches/{matchId}/scores")
    public ResponseEntity<List<Score>> getScoresByMatch(@PathVariable("matchId") int matchId) {
        List<Score> scores = scoreDao.getScoresByMatch(matchId);
        return ResponseEntity.ok(scores);
    }

    @GetMapping("/players/{playerId}/scores")
    public ResponseEntity<List<Score>> getScoresByPlayer(@PathVariable("playerId") int playerId) {
        List<Score> scores = scoreDao.getScoresByPlayer(playerId);
        return ResponseEntity.ok(scores);
    }

    @PostMapping("/scores")
    public ResponseEntity<Void> createScore(@RequestBody Score score) {
        scoreDao.saveScore(score);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/scores/{id}")
    public ResponseEntity<Void> updateScore(@PathVariable("id") int scoreId, @RequestBody Score score) {
        score.setScoreId(scoreId);
        scoreDao.updateScore(score);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/scores/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable("id") int scoreId) {
        scoreDao.deleteScore(scoreId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
