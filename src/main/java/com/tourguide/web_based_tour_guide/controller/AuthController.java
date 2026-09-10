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
import com.tourguide.web_based_tour_guide.dto.ForgotPasswordRequest;
import com.tourguide.web_based_tour_guide.dto.ResetPasswordRequest;
import com.tourguide.web_based_tour_guide.service.PasswordResetService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordResetService passwordResetService;

    public AuthController(
            UserService userService,
            PasswordResetService passwordResetService) {

        this.userService = userService;
        this.passwordResetService = passwordResetService;
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
    // FORGOT PASSWORD
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody ForgotPasswordRequest request) {

        String token =
                passwordResetService.createResetToken(
                        request.getEmail()
                );

        return ResponseEntity.ok(
                "Password reset token: " + token
        );
    }


    // RESET PASSWORD
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestBody ResetPasswordRequest request) {

        passwordResetService.resetPassword(
                request.getToken(),
                request.getNewPassword()
        );

        return ResponseEntity.ok(
                "Password reset successful"
        );
    }
}