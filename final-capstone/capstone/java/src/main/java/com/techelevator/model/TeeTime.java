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

        public TeeTime() {

        }

        public int getTeeTimeId() {
                return teeTimeId;
        }

        public static void setTeeTimeId(int teeTimeId) {
                this.teeTimeId = teeTimeId;
        }

        public String getMatchName() {
                return matchName;
        }

        public static void setMatchName(String matchName) {
                this.matchName = matchName;
        }

        public int getCourseId() {
                return courseId;
        }

        public static void setCourseId(int courseId) {
                this.courseId = courseId;
        }

        public Timestamp getTime() {
                return time;
        }

        public static void setTime(Timestamp time) {
                this.time = time;
        }

        public int getOrganizerId() {
                return organizerId;
        }

        public static void setOrganizerId(int organizerId) {
                this.organizerId = organizerId;
        }

        public int getLeagueId() {
                return leagueId;
        }

        public static void setLeagueId(int leagueId) {
                this.leagueId = leagueId;
        }

        @Override
        public String toString() {
                return "TeeTime{" +
                        "teeTimeId=" + teeTimeId +
                        ", matchName='" + matchName + '\'' +
                        ", courseId=" + courseId +
                        ", time=" + time +
                        ", organizerId=" + organizerId +
                        ", leagueId=" + leagueId +
                        '}';
        }
}
