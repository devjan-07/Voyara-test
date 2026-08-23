package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    List<Destination> findByStatus(String status);

    List<Destination> findByNameContainingIgnoreCase(String name);

    List<Destination> findByCategoryIgnoreCase(String category);
}