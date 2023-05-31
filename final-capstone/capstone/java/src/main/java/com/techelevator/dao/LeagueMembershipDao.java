package com.techelevator.dao;

import com.techelevator.model.LeagueMembership;

import java.util.List;

public interface LeagueMembershipDao {

    LeagueMembership findLeagueMembershipById(int leagueMembershipId);
    List<LeagueMembership> getAllLeagueMemberships();
    List<LeagueMembership> findLeagueMembershipByLeagueId(int leagueId);
    List<LeagueMembership> findLeagueMembershipByUserId(int userId);
    LeagueMembership addLeagueMembership(LeagueMembership leagueMembership);
    LeagueMembership updateLeagueMembership(LeagueMembership leagueMembership, int id);
    void deleteLeagueMembership(int leagueMembershipId);

}
