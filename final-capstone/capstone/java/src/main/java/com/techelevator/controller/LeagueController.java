package com.techelevator.controller;

import com.techelevator.dao.LeagueDao;
import com.techelevator.model.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/league")
public class LeagueController {

   @Autowired
    private LeagueDao leagueDao;

   
    @GetMapping()
    public List<League> getAllLeagues() {
        return leagueDao.getAllLeagues();
    }

    @GetMapping("/{leagueId}")
    public League getLeagueByLeagueId(@PathVariable int leagueId) {
        League league = leagueDao.findLeagueById(leagueId);
        if (league == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "League not found.");
        } else {
            return league;
        }
    }

    @GetMapping("/leaguemembership/{userId}")
    public List<League> getLeaguesByUserID(@PathVariable int userId) {
        return leagueDao.findLeaguesByUserId(userId);
    }

    @GetMapping("leaguelist/{userId}")
    public List<League> getLeaguesByOrganizerId(@PathVariable int userId){
        return leagueDao.findLeaguesByOrganizerId(userId);
    }

    @GetMapping("/{name}")
    public League getLeagueByLeagueName(@PathVariable String name) {
        League league = leagueDao.findLeagueByName(name);
        if (league == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "League not found.");
        } else {
            return league;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public League addLeague(@RequestBody League league) {
        return leagueDao.createLeague(league);
    }

    @PutMapping("/{leagueId}")
    public League updateLeague(@RequestBody League league, @PathVariable int leagueId) {
        League updatedLeague = leagueDao.updateLeague(league, league.getLeagueId());
        if (updatedLeague == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "League not found");
        } else {
            return updatedLeague;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{leagueId}")
    public void deleteLeague(@PathVariable int leagueId) {
        leagueDao.deleteLeague(leagueId);
    }


}
