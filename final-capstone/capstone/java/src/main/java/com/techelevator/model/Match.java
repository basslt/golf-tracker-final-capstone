package com.techelevator.model;

public class Match {
    private int matchId;
    private String matchName;
    private int leagueId;
    private int teeTimeId;

    public Match(int matchId, String matchName, int leagueId, int teeTimeId) {
        this.matchId = matchId;
        this.matchName = matchName;
        this.leagueId = leagueId;
        this.teeTimeId = teeTimeId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public void setTeeTimeId(int teeTimeId) {
        this.teeTimeId = teeTimeId;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public int getTeeTimeId() {
        return teeTimeId;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", matchName='" + matchName + '\'' +
                ", leagueId=" + leagueId +
                ", teeTimeId=" + teeTimeId +
                '}';
    }
}
