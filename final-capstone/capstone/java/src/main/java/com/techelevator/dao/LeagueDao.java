package com.techelevator.dao;

import com.techelevator.model.League;

import java.util.List;

public interface LeagueDao {

    List<League> getAllLeagues();
    League findLeagueById(int leagueId);
    League findLeagueByName(String leagueName);
    League createLeague(League league);
    League updateLeague(League league, int id);
    void deleteLeague(int leagueId);


}
