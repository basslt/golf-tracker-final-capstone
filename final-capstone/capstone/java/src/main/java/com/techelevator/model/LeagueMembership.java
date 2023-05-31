package com.techelevator.model;

public class LeagueMembership {

    private int leagueMembershipId;
    private int leagueId ;
    private int userId;


    public LeagueMembership(int leagueMembershipId, int leagueId, int userId) {
        this.leagueMembershipId = leagueMembershipId;
        this.leagueId = leagueId;
        this.userId = userId;
    }

    public LeagueMembership() {
    }

    public int getLeagueMembershipId() {
        return leagueMembershipId;
    }

    public void setLeagueMembershipId(int leagueMembershipId) {
        this.leagueMembershipId = leagueMembershipId;
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

    @Override
    public String toString() {
        return "LeagueMembership{" +
                "leagueMembershipId=" + leagueMembershipId +
                ", leagueId=" + leagueId +
                ", userId=" + userId +
                '}';
    }
}
