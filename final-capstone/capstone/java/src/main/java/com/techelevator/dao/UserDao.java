package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);
    List<String> getAllUsernames();
    int getUserIdByUsername(String username);

    User findByUsername(String username);

    List<User> findUsersNotInLeague(int leagueId);

    int findIdByUsername(String username);
    String findUsernameById(int userId);

    List<User> findUsersInLeague(int leagueId);

    List<User> findUsersInMatch(int matchId);

    boolean create(String username, String password, String role);
}
