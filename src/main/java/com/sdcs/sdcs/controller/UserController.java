package com.sdcs.sdcs.controller;

import com.sdcs.sdcs.model.User;
import com.sdcs.sdcs.repository.UserRepository;
import com.sdcs.sdcs.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean success = userService.login(user.getUsername(), user.getPassword());
        if (success) {
            User loggedInUser = userRepository.findByUsername(user.getUsername());
            return ResponseEntity.ok(loggedInUser);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}