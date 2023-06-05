package com.techelevator.controller;

import com.techelevator.dao.LeagueInviteDao;
import com.techelevator.model.LeagueInvite;
import com.techelevator.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
public class LeagueInviteController {

    @Autowired
    private LeagueInviteDao inviteDao;

    @GetMapping("/invites/{id}")
    public LeagueInvite getLeagueInviteById(@PathVariable int leagueId) {
        LeagueInvite invite = inviteDao.findById(leagueId);
        if (invite == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invite not found.");
        } else {
            return invite;
        }
    }

    @GetMapping("/invites")
    public List<LeagueInvite> getAllInvites() {
        return inviteDao.getAllLeagueInvites();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/invites")
    public LeagueInvite createInvite(@RequestBody LeagueInvite invite) {
        return inviteDao.saveLeagueInvite(invite);
    }

    @PutMapping("/invites/{id}")
    public ResponseEntity<Void> updateLeagueInvite(@PathVariable("id") int messageId, @RequestBody LeagueInvite invite) {
        inviteDao.updateLeagueInvite(invite, invite.getLeagueInviteId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/invites/{id}")
    public ResponseEntity<Void> deleteLeagueInvite(@PathVariable("id") int inviteId) {
        inviteDao.deleteLeagueInvite(inviteId);
        return ResponseEntity.noContent().build();
    }
}
