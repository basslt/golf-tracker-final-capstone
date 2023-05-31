package com.techelevator.dao;

import com.techelevator.model.LeagueMembership;

import java.util.List;

public class JdbcLeagueMembershipDao implements LeagueMembershipDao {
    
    @Override
    public LeagueMembership findLeagueMembershipById(int leagueMembershipId) {
        return null;
    }

    @Override
    public List<LeagueMembership> getAllLeagueMemberships() {
        return null;
    }

    @Override
    public List<LeagueMembership> findLeagueMembershipByLeagueId(int leagueId) {
        return null;
    }

    @Override
    public List<LeagueMembership> findLeagueMembershipByUserId(int userId) {
        return null;
    }

    @Override
    public LeagueMembership addLeagueMembership(LeagueMembership leagueMembership) {
        return null;
    }

    @Override
    public LeagueMembership updateLeagueMembership(LeagueMembership leagueMembership, int id) {
        return null;
    }

    @Override
    public void deleteLeagueMembership(int leagueMembershipId) {

    }
}
