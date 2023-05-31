package com.techelevator.controller;

import com.techelevator.dao.MatchDao;
import com.techelevator.model.Match;
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
@RequestMapping("/matches")
public class MatchController {
    private final MatchDao matchDao;

    @Autowired
    public MatchController(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable int matchId) throws ChangeSetPersister.NotFoundException {
        Match match = matchDao.findById(matchId);
        if (match != null) {
            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchDao.getAllMatches();
    }

    @GetMapping(params = "leagueId")
    public List<Match> getMatchesByLeague(@RequestParam int leagueId) {
        return matchDao.getMatchesByLeague(leagueId);
    }

    @PostMapping
    public ResponseEntity<Match> addMatch(@RequestBody Match match) {
        matchDao.saveMatch(match);
        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }

    @PutMapping("/{matchId}")
    public ResponseEntity<Match> updateMatch(@PathVariable int matchId, @RequestBody Match match) throws ChangeSetPersister.NotFoundException {
        Match existingMatch = matchDao.findById(matchId);
        if (existingMatch != null) {
            match.setMatchId(matchId);
            matchDao.updateMatch(match);
            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable int matchId) throws ChangeSetPersister.NotFoundException {
        Match existingMatch = matchDao.findById(matchId);
        if (existingMatch != null) {
            matchDao.deleteMatch(matchId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
