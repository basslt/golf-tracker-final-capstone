package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.List;

public class TeeTime {
    private int teeTimeId;
    private LocalDateTime dateTime;
    private int courseId;
    private List<Integer> playerId;
    private int organizerId;

    public TeeTime(int teeTimeId, LocalDateTime dateTime, int courseId, List<Integer> playerId, int organizerId) {
        this.teeTimeId = teeTimeId;
        this.dateTime = dateTime;
        this.courseId = courseId;
        this.playerId = playerId;
        this.organizerId = organizerId;
    }

    public int getTeeTimeId() {
        return teeTimeId;
    }

    public void setTeeTimeId(int teeTimeId) {
        this.teeTimeId = teeTimeId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<Integer> getPlayerId() {
        return playerId;
    }

    public void setPlayerId(List<Integer> playerId) {
        this.playerId = playerId;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }
}