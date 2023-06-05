package com.techelevator.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class TeeTime {
        private int teeTimeId;
        private String matchName;
        private int courseId;
        private Timestamp time;
        private int organizerId;
        private int leagueId;


        public TeeTime(int teeTimeId, String matchName, int courseId, Timestamp time, int organizerId, int leagueId) {
                this.teeTimeId = teeTimeId;
                this.matchName = matchName;
                this.courseId = courseId;
                this.time = time;
                this.organizerId = organizerId;
                this.leagueId = leagueId;
        }

        public int getTeeTimeId() {
                return teeTimeId;
        }

        public void setTeeTimeId(int teeTimeId) {
                this.teeTimeId = teeTimeId;
        }

        public String getMatchName() {
                return matchName;
        }

        public void setMatchName(String matchName) {
                this.matchName = matchName;
        }

        public int getCourseId() {
                return courseId;
        }

        public void setCourseId(int courseId) {
                this.courseId = courseId;
        }

        public Timestamp getTime() {
                return time;
        }

        public void setTime(Timestamp time) {
                this.time = time;
        }

        public int getOrganizerId() {
                return organizerId;
        }

        public void setOrganizerId(int organizerId) {
                this.organizerId = organizerId;
        }

        public int getLeagueId() {
                return leagueId;
        }

        public void setLeagueId(int leagueId) {
                this.leagueId = leagueId;
        }
}
