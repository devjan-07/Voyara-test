package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.dto.UserDTO;
import com.tourguide.web_based_tour_guide.entity.User;
import com.tourguide.web_based_tour_guide.exception.ApiException;
import com.tourguide.web_based_tour_guide.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GET ALL USERS
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // GET USER BY ID
    public UserDTO getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ApiException(
                                "User not found",
                                404
                        ));

        return convertToDTO(user);
    }

    // CHANGE USER ROLE
    public UserDTO changeUserRole(
            Integer id,
            String role) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ApiException(
                                "User not found",
                                404
                        ));

        String newRole = role.toUpperCase();

        validateRole(newRole);

        user.setRole(newRole);

        User updatedUser =
                userRepository.save(user);

        return convertToDTO(updatedUser);
    }

    // CHANGE USER STATUS
    public UserDTO changeUserStatus(
            Integer id,
            String status) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ApiException(
                                "User not found",
                                404
                        ));

        String newStatus = status.toUpperCase();

        if (!newStatus.equals("ACTIVE") &&
                !newStatus.equals("INACTIVE")) {

            throw new ApiException(
                    "Invalid status. Use ACTIVE or INACTIVE",
                    400
            );
        }

        user.setStatus(newStatus);

        User updatedUser =
                userRepository.save(user);

        return convertToDTO(updatedUser);
    }

    // CREATE PROFESSIONAL USER
    public UserDTO createProfessionalUser(
            String fullName,
            String email,
            String password,
            String role) {

        if (userRepository.existsByEmail(email)) {

            throw new ApiException(
                    "A user with this email already exists",
                    409
            );
        }

        String newRole = role.toUpperCase();

        validateProfessionalRole(newRole);

        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);

        user.setPasswordHash(
                passwordEncoder.encode(password)
        );

        user.setRole(newRole);
        user.setStatus("ACTIVE");

        User savedUser =
                userRepository.save(user);

        return convertToDTO(savedUser);
    }

    // VALIDATE ROLE
    private void validateRole(String role) {

        if (!role.equals("TOURIST") &&
                !role.equals("TOUR_GUIDE") &&
                !role.equals("AGENCY_STAFF") &&
                !role.equals("CONTENT_MANAGER") &&
                !role.equals("ADMIN")) {

            throw new ApiException(
                    "Invalid role",
                    400
            );
        }
    }

    // VALIDATE PROFESSIONAL ROLE
    private void validateProfessionalRole(
            String role) {

        if (!role.equals("TOUR_GUIDE") &&
                !role.equals("AGENCY_STAFF") &&
                !role.equals("CONTENT_MANAGER")) {

            throw new ApiException(
                    "Professional account must be TOUR_GUIDE, " +
                            "AGENCY_STAFF, or CONTENT_MANAGER",
                    400
            );
        }
    }

    // ENTITY → DTO
    private UserDTO convertToDTO(User user) {

        return new UserDTO(
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getStatus()
        );
    }
}