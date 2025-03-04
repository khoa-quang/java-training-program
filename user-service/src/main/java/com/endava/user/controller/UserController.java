package com.endava.user.controller;

import com.endava.user.model.User;
import com.endava.user.service.UserService;
import com.endava.user.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users - Retrieve all users
    @GetMapping("all")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userService.getAllUsers();
        log.debug("Retrieved users details: {}", users);
        return ResponseEntity.ok(users);
    }

    // GET /api/users/{id} - Retrieve a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("Fetching user with id: {}", id);
        User user = userService.getUserById(id);
        log.debug("Retrieved user details with id {}: {}", id, user);
        return ResponseEntity.ok(user);
    }

    // POST /api/users/register - Create a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        log.info("Creating user: {}", user);
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        User createdUser = userService.createUser(user);
        log.debug("Created user: {}", createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // PUT /api/users/{id} - Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        log.info("Updating user with id: {}", id);
        User updatedUser = userService.updateUser(id, user);
        log.debug("Updated user with id {}: {}", id, updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE /api/users/{id} - Soft delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
