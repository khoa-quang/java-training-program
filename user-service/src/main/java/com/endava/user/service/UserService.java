package com.endava.user.service;

import com.endava.user.exceptions.UserNotFoundException;
import com.endava.user.model.User;
import com.endava.user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    // Retrieve a single user by ID
    public User getUserById(Long id) {
        log.info("Fetching user with id: {}",id);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            log.warn("User with id {} not found", id);
            throw new UserNotFoundException("User not found with id " + id);
        }
    }

    // Create a new user
    public User createUser(User user) {
        log.info("Saving user: {}", user);
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(Long id, User userDetails) {
        log.info("Updating user with id {}: {}", id, userDetails);
        User user = getUserById(id);
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setAddress(userDetails.getAddress());
        user.setDeleted(userDetails.getDeleted());
        return userRepository.save(user);
    }

    // Soft delete a user (mark as deleted)
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        User user = getUserById(id);
        user.setDeleted(true);
        userRepository.save(user);
    }
}
