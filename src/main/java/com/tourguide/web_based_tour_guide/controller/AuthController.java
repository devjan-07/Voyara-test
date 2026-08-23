package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.dto.LoginRequest;
import com.tourguide.web_based_tour_guide.dto.LoginResponse;
import com.tourguide.web_based_tour_guide.dto.UserDTO;
import com.tourguide.web_based_tour_guide.entity.User;
import com.tourguide.web_based_tour_guide.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tourguide.web_based_tour_guide.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(
            @Valid @RequestBody RegisterRequest request) {

        UserDTO registeredUser =
                userService.registerUser(request);

        return new ResponseEntity<>(
                registeredUser,
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                userService.loginUser(request);

        return ResponseEntity.ok(response);
    }
}