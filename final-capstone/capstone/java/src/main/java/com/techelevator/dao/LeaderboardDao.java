package com.techelevator.dao;

import com.techelevator.model.Leaderboard;

import java.util.List;
import java.util.Map;

public interface LeaderboardDao {

    List<Leaderboard> getAllLeaderboardsOrdered(int leagueId);

//    Leaderboard getById(int leaderboardId);
//
//    List<Leaderboard> getByLeagueId(int leagueId);
//
//    List<Leaderboard> getByUserId(int userId);

    void add(Leaderboard leaderboard);

    void update(Leaderboard leaderboard);

    void delete(int leaderboardId);

    List<Map<String, Object>> getTopPlayersByScoringAverage();
}
