package com.techelevator.model;

public class League {

    private int leagueId;
    private String leagueName;
    private int organizerId;

    public League(int leagueId, String leagueName, int organizerId) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.organizerId = organizerId;
    }

    public League() {
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    @Override
    public String toString() {
        return "League{" +
                "leagueId=" + leagueId +
                ", leagueName='" + leagueName + '\'' +
                ", organizerId=" + organizerId +
                '}';
    }
}
