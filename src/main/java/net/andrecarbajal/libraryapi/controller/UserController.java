package net.andrecarbajal.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.andrecarbajal.libraryapi.domain.role.Role;
import net.andrecarbajal.libraryapi.domain.role.RoleRepository;
import net.andrecarbajal.libraryapi.domain.user.User;
import net.andrecarbajal.libraryapi.domain.user.UserDTO;
import net.andrecarbajal.libraryapi.domain.user.UserRecord;
import net.andrecarbajal.libraryapi.domain.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/register-admin")
    @Transactional
    @Operation(summary = "Register a new admin user")
    public ResponseEntity<User> registerAdmin(@RequestBody UserRecord data) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElse(null);
        User user = User.builder()
                .username(data.username())
                .password(passwordEncoder.encode(data.password()))
                .roles(Collections.singleton(adminRole)).build();
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream().map(user -> UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles()).build()).collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }
}