package com.techelevator.dao;

import com.techelevator.model.Score;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ScoreDao {

    Score findLatestPlayerScore(int userId, int leagueId);

    Score findById(int scoreId);
    List<Score> getAllScores();
    List<Score> getScoresByMatch(int matchId);
    List<Score> getScoresByPlayer(int playerId);
    Score saveScore(Score score);
    Score updateScore(Score score, int scoreId);
    void deleteScore(int scoreId);
}
