package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.dto.LoginRequest;
import com.tourguide.web_based_tour_guide.dto.LoginResponse;
import com.tourguide.web_based_tour_guide.dto.RegisterRequest;
import com.tourguide.web_based_tour_guide.dto.UserDTO;
import com.tourguide.web_based_tour_guide.entity.User;
import com.tourguide.web_based_tour_guide.exception.ApiException;
import com.tourguide.web_based_tour_guide.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    // CREATE
    public UserDTO createUser(User user) {

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    // READ ALL
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // READ BY ID
    public UserDTO getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID: " + id
                        ));

        return convertToDTO(user);
    }

    // READ BY EMAIL
    public UserDTO getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with email: " + email
                        ));

        return convertToDTO(user);
    }

    // READ BY ROLE
    public List<UserDTO> getUsersByRole(String role) {

        return userRepository.findByRoleIgnoreCase(role)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // READ BY STATUS
    public List<UserDTO> getUsersByStatus(String status) {

        return userRepository.findByStatusIgnoreCase(status)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // UPDATE
    public UserDTO updateUser(Integer id, User user) {

        User existing = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID: " + id
                        ));

        existing.setFullName(user.getFullName());
        existing.setEmail(user.getEmail());
        existing.setPasswordHash(user.getPasswordHash());
        existing.setPhone(user.getPhone());
        existing.setRole(user.getRole());
        existing.setStatus(user.getStatus());

        User updatedUser = userRepository.save(existing);

        return convertToDTO(updatedUser);
    }

    // DELETE
    public void deleteUser(Integer id) {

        User existing = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID: " + id
                        ));

        userRepository.delete(existing);
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

    // REGISTER
    public UserDTO registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new ApiException(
                    "A user with this email already exists",
                    409
            );
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword())
        );

        // New users are tourists by default
        user.setRole("TOURIST");

        // New users are active by default
        user.setStatus("ACTIVE");

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    // LOGIN
    public LoginResponse loginUser(LoginRequest request) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

        } catch (AuthenticationException e) {

            throw new ApiException(
                    "Invalid email or password",
                    401
            );
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ApiException(
                                "Invalid email or password",
                                401
                        )
                );

        return new LoginResponse(
                "Login successful",
                user.getUserId(),
                user.getFullName(),
                user.getRole()
        );
    }
}