package com.techelevator.dao;

import com.techelevator.model.MatchPlayer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface MatchPlayerDao {
    MatchPlayer findById(int matchPlayerId);

    List<MatchPlayer> getAllMatchPlayers();

    List<MatchPlayer> findMatchPlayersByMatch(int matchId);

    List<MatchPlayer> findMatchPlayersByPlayer(int playerId);

    MatchPlayer saveMatchPlayer(MatchPlayer matchPlayer);

    void updateMatchPlayer(MatchPlayer matchPlayer);

    void deleteMatchPlayer(int matchPlayerId);

    int getMatchIdByPlayerId(int playerId);

    MatchPlayer mapRowToMatchPlayer(SqlRowSet rowSet);
}
