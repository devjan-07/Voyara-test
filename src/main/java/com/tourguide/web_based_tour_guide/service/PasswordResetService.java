package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.User;
import com.tourguide.web_based_tour_guide.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Stores reset tokens temporarily while the application is running
    private final Map<String, ResetToken> resetTokens = new HashMap<>();

    public PasswordResetService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // REQUEST PASSWORD RESET
    public String createResetToken(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with this email"
                        ));

        String token = UUID.randomUUID().toString();

        // Token is valid for 15 minutes
        resetTokens.put(
                token,
                new ResetToken(
                        user.getUserId(),
                        LocalDateTime.now().plusMinutes(15)
                )
        );

        return token;
    }

    // RESET PASSWORD
    public void resetPassword(
            String token,
            String newPassword) {

        ResetToken resetToken =
                resetTokens.get(token);

        if (resetToken == null) {
            throw new RuntimeException(
                    "Invalid reset token"
            );
        }

        if (LocalDateTime.now()
                .isAfter(resetToken.expiresAt)) {

            resetTokens.remove(token);

            throw new RuntimeException(
                    "Reset token has expired"
            );
        }

        if (newPassword == null ||
                newPassword.length() < 6) {

            throw new IllegalArgumentException(
                    "Password must contain at least 6 characters"
            );
        }

        User user = userRepository
                .findById(resetToken.userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        ));

        user.setPasswordHash(
                passwordEncoder.encode(newPassword)
        );

        userRepository.save(user);

        // Token can only be used once
        resetTokens.remove(token);
    }

    // Small class used to store token information
    private static class ResetToken {

        private final Integer userId;
        private final LocalDateTime expiresAt;

        public ResetToken(
                Integer userId,
                LocalDateTime expiresAt) {

            this.userId = userId;
            this.expiresAt = expiresAt;
        }
    }
}