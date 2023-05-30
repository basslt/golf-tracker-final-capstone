package com.techelevator.dao;

import com.techelevator.model.League;

public interface LeagueDao {

    League findLeagueById(int leagueId);
    League findLeagueByName(String leagueName);
    League createLeague(League league);
    League updateLeague(League league, int id);
    void deleteLeague(int leagueId);


}
