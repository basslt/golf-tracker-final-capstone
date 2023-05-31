package com.techelevator.dao;

import com.techelevator.model.Match;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface MatchDao {
    Match findById(int matchId) throws ChangeSetPersister.NotFoundException;

    List<Match> getAllMatches();

    List<Match> getMatchesByLeague(int leagueId);

    void saveMatch(Match match);

    void updateMatch(Match match);

    void deleteMatch(int matchId);
}
