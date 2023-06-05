package com.techelevator.dao;

import com.techelevator.model.LeagueInvite;

import java.util.List;

public interface LeagueInviteDao {

    LeagueInvite findById(int leagueInviteId);

    List<LeagueInvite> getAllLeagueInvites();

    List<LeagueInvite> getAllLeagueInvitesBySenderId(int senderId);

    List<LeagueInvite> getAllLeagueInvitesByReceiverId(int receiverId);

    LeagueInvite saveLeagueInvite(LeagueInvite leagueInvite);

    LeagueInvite updateLeagueInvite(LeagueInvite leagueInvite, int id);

    void deleteLeagueInvite(int leagueInviteId);


}
