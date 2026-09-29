package com.example.springbackend.service;

import com.example.springbackend.exception.UserNotFoundException;
import com.example.springbackend.model.User;
import com.example.springbackend.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + id + " was not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User with email " + email + " was not found"));
    }
}