package com.techelevator.controller;

import com.techelevator.dao.LeagueMembershipDao;
import com.techelevator.model.LeagueMembership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class LeagueMembershipController {

    @Autowired
    LeagueMembershipDao leagueMembershipDao;

    @GetMapping("/leaguemembership")
    public List<LeagueMembership> getAllLeagueMemberships() {
        return leagueMembershipDao.getAllLeagueMemberships();
    }

//    @GetMapping("/leaguemembership/{id}")
//    public LeagueMembership getLeagueMembershipByLeagueMembershipId(@PathVariable int leagueMembershipId) {
//        LeagueMembership leagueMembership = leagueMembershipDao.findLeagueMembershipById(leagueMembershipId);
//        if (leagueMembership == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LeagueMembership not found.");
//        } else {
//            return leagueMembership;
//        }
//    }

    @GetMapping("/league/{leagueId}/leaguemembership")
    public List<LeagueMembership> getLeagueMembershipByLeagueId(@PathVariable("leagueId") int leagueId) {
        return leagueMembershipDao.findLeagueMembershipByLeagueId(leagueId);
    }

//    @GetMapping("/leaguemembership/{leagueId}")
//    public List<LeagueMembership> getLeagueMembershipByLeagueId(@PathVariable int leagueId) {
//        return leagueMembershipDao.findLeagueMembershipByLeagueId(leagueId);
//    }

//    @GetMapping("/leaguemembership/league/{leagueId}")
//    public List<LeagueMembership> getLeagueMembershipByLeagueId(@PathVariable int leagueId) {
//        return leagueMembershipDao.findLeagueMembershipByLeagueId(leagueId);
//    }

    @GetMapping("/users/{userId}/leaguemembership")
    public List<LeagueMembership> getLeagueMembershipByUserId(@PathVariable int userId) {
        return leagueMembershipDao.findLeagueMembershipByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/leaguemembership")
    public LeagueMembership addLeagueMembership(@RequestBody LeagueMembership leagueMembership) {
        return leagueMembershipDao.addLeagueMembership(leagueMembership);
    }

    @PutMapping("/leaguemembership/{id}")
    public LeagueMembership updateLeagueMembership(@RequestBody LeagueMembership leagueMembership, @PathVariable int leagueMembershipId) {
        LeagueMembership updatedLeagueMembership = leagueMembershipDao.updateLeagueMembership(leagueMembership, leagueMembership.getLeagueMembershipId());
        if (updatedLeagueMembership == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LeagueMembership not found");
        } else {
            return updatedLeagueMembership;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leaguemembership/{id}")
    public void deleteLeagueMembership(@PathVariable int leagueMembershipId) {
        leagueMembershipDao.deleteLeagueMembership(leagueMembershipId);
    }




}
