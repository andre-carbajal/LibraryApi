package net.andrecarbajal.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.andrecarbajal.libraryapi.domain.role.Role;
import net.andrecarbajal.libraryapi.domain.role.RoleRepository;
import net.andrecarbajal.libraryapi.domain.user.User;
import net.andrecarbajal.libraryapi.domain.user.UserRecord;
import net.andrecarbajal.libraryapi.domain.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Tag(name = "User Controller")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @Transactional
    @Operation(summary = "Register a new user")
    public ResponseEntity<User> registerUser(@RequestBody UserRecord data) {
        Role userRole = roleRepository.findByName("ROLE_USER").orElse(null);
        User user = User.builder()
                .username(data.username())
                .password(passwordEncoder.encode(data.password()))
                .roles(Collections.singleton(userRole)).build();
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
