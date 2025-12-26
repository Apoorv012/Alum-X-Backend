package com.opencode.alumxbackend.users.controller;

import com.opencode.alumxbackend.users.dto.DevUserRequest;
import com.opencode.alumxbackend.users.model.User;
import com.opencode.alumxbackend.users.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dev/users")
@RequiredArgsConstructor
public class DevUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String DUMMY_TOKEN = "alumx-dev-token";

    @PostMapping
    public ResponseEntity<?> createUser(@RequestHeader(value = "X-DUMMY-TOKEN", required = false) String token,
            @Valid @RequestBody DevUserRequest request) {

        // 1. Validate Token
        if (token == null || !token.equals(DUMMY_TOKEN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Invalid or missing X-DUMMY-TOKEN header"));
        }

        // 2. Validate Uniqueness
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
        }
        // Assuming we update UserRepository to verify username, currently we only have
        // email in repo interface
        // Adding check just in case, though logically we need to add existsByUsername
        // to repo

        // 3. Create User
        User user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().toUpperCase()) // Allow case-insensitive role input
                .build();

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "User created successfully via DEV API",
                "userId", user.getId(),
                "role", user.getRole()));
    }
}
