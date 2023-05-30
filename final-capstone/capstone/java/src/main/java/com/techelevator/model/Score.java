package com.techelevator.model;

public class Score {
    private int scoreId;
    private int matchId;
    private int playerId;
    private int score;

    public Score(int scoreId, int matchId, int playerId, int score) {
        this.scoreId = scoreId;
        this.matchId = matchId;
        this.playerId = playerId;
        this.score = score;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", matchId=" + matchId +
                ", playerId=" + playerId +
                ", score=" + score +
                '}';
    }
}