package com.techelevator.controller;
import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
//@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable int userId) {
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        } else {
            return user;
        }
    }

//    @GetMapping("/{username}")
//    public User findByUsername(@PathVariable String username) {
//        User user = userDao.findByUsername(username);
//        return user;
//    }

    @GetMapping("")
    public List<User> findAll() {
        return userDao.findAll();
    }

    @GetMapping("/leaguemembership/not-in-league/{leagueId}")
    public List<User> findUsersNotInLeague(@PathVariable("leagueId") int leagueId) {
        return userDao.findUsersNotInLeague(leagueId);
    }

    @GetMapping("/leaguemembership/league/{leagueId}")
    public List<User> findUsersInLeague(@PathVariable("leagueId") int leagueId) {
        return userDao.findUsersInLeague(leagueId);
    }

    @GetMapping("/matches/{matchId}")
    public List<User> findUsersInMatch(@PathVariable("matchId") int matchId) {
        return userDao.findUsersInMatch(matchId);
    }

    @GetMapping("/username")
    public List<String> getAllUsernames() {
        return userDao.getAllUsernames();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        return userDao.create(username, password, role);
    }

//    @GetMapping("/id/{username}")
//    public int getUserIdByUsername(@PathVariable String username) {
//        return userDao.findIdByUsername(username);
//    }

    @GetMapping("/{username}/id")
    public int getUserIdByUsername(@PathVariable String username) {
        return userDao.getUserIdByUsername(username);
    }

    @GetMapping("/{userId}/username")
    public String getUsernameById(@PathVariable int userId) {
        return userDao.findUsernameById(userId);

    }
}



