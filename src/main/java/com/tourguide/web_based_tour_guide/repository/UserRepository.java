package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findByRoleIgnoreCase(String role);

    List<User> findByStatusIgnoreCase(String status);

    boolean existsByEmail(String email);
}