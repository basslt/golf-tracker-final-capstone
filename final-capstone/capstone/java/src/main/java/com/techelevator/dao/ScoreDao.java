package com.techelevator.dao;

import com.techelevator.model.Score;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ScoreDao {
    Score findById(int scoreId) throws ChangeSetPersister.NotFoundException;
    List<Score> getAllScores();
    List<Score> getScoresByMatch(int matchId);
    List<Score> getScoresByPlayer(int playerId);
    void saveScore(Score score);
    void updateScore(Score score);
    void deleteScore(int scoreId);
}
