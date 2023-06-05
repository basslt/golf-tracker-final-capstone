package com.techelevator.model;

import java.sql.Timestamp;

public class LeagueInvite {

    private int leagueInviteId;
    private int senderId;
    private int receiverId;
    private int leagueId;
    private String content;
    private String status;
    private Timestamp timestamp;

    public LeagueInvite(int leagueInviteId, int senderId, int receiverId, int leagueId, String content, Timestamp timestamp) {
        this.leagueInviteId = leagueInviteId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.leagueId = leagueId;
        this.content = content;
        this.status = "Not Accepted";
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LeagueInvite() {
    }

    public int getLeagueInviteId() {
        return leagueInviteId;
    }

    public void setLeagueInviteId(int leagueInviteId) {
        this.leagueInviteId = leagueInviteId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LeagueInvite{" +
                "leagueInviteId=" + leagueInviteId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", leagueId=" + leagueId +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
