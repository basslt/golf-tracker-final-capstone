package com.techelevator.controller;
import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;

    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        try {
            User user = userDao.findByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userDao.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        return userDao.create(username, password, role);
    }

    @GetMapping("/{username}/id")
    public int findIdByUsername(@PathVariable String username) {
        return userDao.findIdByUsername(username);
    }

    @GetMapping("/{userId}/username")
    public ResponseEntity<String> getUsernameById(@PathVariable int userId) {
        String username = userDao.findUsernameById(userId);
        if (username != null) {
            return ResponseEntity.ok(username);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
