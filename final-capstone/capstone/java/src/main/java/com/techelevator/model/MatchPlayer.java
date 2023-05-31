package com.techelevator.model;

public class MatchPlayer {
    private int matchPlayerId;
    private int matchId;
    private int playerId;


    public MatchPlayer(int matchPlayerId, int matchId, int playerId) {
        this.matchPlayerId = matchPlayerId;
        this.matchId = matchId;
        this.playerId = playerId;
    }

    public int getMatchPlayerId() {
        return matchPlayerId;
    }

    public void setMatchPlayerId(int matchPlayerId) {
        this.matchPlayerId = matchPlayerId;
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

    @Override
    public String toString() {
        return "MatchPlayer{" +
                "matchPlayerId=" + matchPlayerId +
                ", matchId=" + matchId +
                ", playerId=" + playerId +
                '}';
    }
}