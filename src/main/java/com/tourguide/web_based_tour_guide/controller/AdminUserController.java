package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.dto.UserDTO;
import com.tourguide.web_based_tour_guide.service.AdminUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    // GET ALL USERS
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        return ResponseEntity.ok(
                adminUserService.getAllUsers()
        );
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                adminUserService.getUserById(id)
        );
    }

    // CHANGE USER ROLE
    @PutMapping("/{id}/role")
    public ResponseEntity<UserDTO> changeUserRole(
            @PathVariable Integer id,
            @RequestParam String role) {

        return ResponseEntity.ok(
                adminUserService.changeUserRole(id, role)
        );
    }

    // CHANGE USER STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<UserDTO> changeUserStatus(
            @PathVariable Integer id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                adminUserService.changeUserStatus(id, status)
        );
    }

    // CREATE PROFESSIONAL USER
    @PostMapping
    public ResponseEntity<UserDTO> createProfessionalUser(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role) {

        UserDTO createdUser =
                adminUserService.createProfessionalUser(
                        fullName,
                        email,
                        password,
                        role
                );

        return new ResponseEntity<>(
                createdUser,
                HttpStatus.CREATED
        );
    }
}