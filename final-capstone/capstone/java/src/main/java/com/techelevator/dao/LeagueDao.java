package com.techelevator.dao;

import com.techelevator.model.League;

import java.util.List;

public interface LeagueDao {

    List<League> getAllLeagues();
    League findLeagueById(int leagueId);
    League findLeagueByName(String leagueName);
    List<League> findLeaguesByUserId(int userId);
    List<League> findLeaguesByOrganizerId(int userId);
    League createLeague(League league);
    League updateLeague(League league, int id);
    void deleteLeague(int leagueId);


}
