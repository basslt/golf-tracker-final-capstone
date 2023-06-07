package com.techelevator.model;

public class Leaderboard {
    private int leaderboardId;
    private int leagueId;
    private int userId;
    private int totalScore;


    public Leaderboard(int leaderboardId, int leagueId, int userId, int totalScore) {
        this.leaderboardId = leaderboardId;
        this.leagueId = leagueId;
        this.userId = userId;
        this.totalScore = totalScore;
    }

    public Leaderboard() {
    }

    public int getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(int leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
