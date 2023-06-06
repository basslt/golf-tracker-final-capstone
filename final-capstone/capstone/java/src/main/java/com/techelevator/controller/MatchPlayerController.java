package com.techelevator.controller;

import com.techelevator.dao.MatchPlayerDao;
import com.techelevator.model.MatchPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
public class MatchPlayerController {
    private final MatchPlayerDao matchPlayerDao;

    @Autowired
    public MatchPlayerController(MatchPlayerDao matchPlayerDao) {
        this.matchPlayerDao = matchPlayerDao;
    }

    @GetMapping("/matchplayers/{id}")
    public ResponseEntity<MatchPlayer> getMatchPlayerById(@PathVariable("id") int matchPlayerId) throws ChangeSetPersister.NotFoundException {
        MatchPlayer matchPlayer = matchPlayerDao.findById(matchPlayerId);
        if (matchPlayer == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return ResponseEntity.ok(matchPlayer);
    }

    @GetMapping("/matchplayers")
    public ResponseEntity<List<MatchPlayer>> getAllMatchPlayers() {
        List<MatchPlayer> matchPlayers = matchPlayerDao.getAllMatchPlayers();
        return ResponseEntity.ok(matchPlayers);
    }

    @GetMapping("/matches/{matchId}/matchplayers")
    public ResponseEntity<List<MatchPlayer>> getMatchPlayersByMatch(@PathVariable("matchId") int matchId) {
        List<MatchPlayer> matchPlayers = matchPlayerDao.findMatchPlayersByMatch(matchId);
        return ResponseEntity.ok(matchPlayers);
    }

    @GetMapping("/players/{playerId}/matchplayers")
    public ResponseEntity<List<MatchPlayer>> getMatchPlayersByPlayer(@PathVariable("playerId") int playerId) {
        List<MatchPlayer> matchPlayers = matchPlayerDao.findMatchPlayersByPlayer(playerId);
        return ResponseEntity.ok(matchPlayers);
    }

    @PostMapping("/matchplayers")
    public ResponseEntity<Void> createMatchPlayer(@RequestBody MatchPlayer matchPlayer) {
        matchPlayerDao.saveMatchPlayer(matchPlayer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/matchplayers/{id}")
    public ResponseEntity<Void> updateMatchPlayer(@PathVariable("id") int matchPlayerId, @RequestBody MatchPlayer matchPlayer) {
        matchPlayer.setMatchPlayerId(matchPlayerId);
        matchPlayerDao.updateMatchPlayer(matchPlayer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/matchplayers/{id}")
    public ResponseEntity<Void> deleteMatchPlayer(@PathVariable("id") int matchPlayerId) {
        matchPlayerDao.deleteMatchPlayer(matchPlayerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/matchplayers/{playerId}/matchId")
    public int getMatchIdByPlayerId(@PathVariable("playerId") int playerId) {
        return matchPlayerDao.getMatchIdByPlayerId(playerId);
    }
}
