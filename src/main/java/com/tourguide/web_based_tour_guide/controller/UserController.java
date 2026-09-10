package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.dto.UserDTO;
import com.tourguide.web_based_tour_guide.entity.User;
import com.tourguide.web_based_tour_guide.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<UserDTO> create(
            @RequestBody User user) {

        UserDTO created = userService.createUser(user);

        return new ResponseEntity<>(
                created,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }

    // READ BY EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getByEmail(
            @PathVariable String email) {

        return ResponseEntity.ok(
                userService.getUserByEmail(email)
        );
    }

    // READ BY ROLE
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDTO>> getByRole(
            @PathVariable String role) {

        return ResponseEntity.ok(
                userService.getUsersByRole(role)
        );
    }

    // READ BY STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<List<UserDTO>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                userService.getUsersByStatus(status)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(
            @PathVariable Integer id,
            @RequestBody User user) {

        return ResponseEntity.ok(
                userService.updateUser(id, user)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
    // GET CURRENT PROFILE
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getMyProfile(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                userService.getCurrentUserProfile(email)
        );
    }


    // UPDATE CURRENT PROFILE
    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateMyProfile(
            Authentication authentication,
            @RequestBody User user) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                userService.updateCurrentUserProfile(
                        email,
                        user
                )
        );
    }
}