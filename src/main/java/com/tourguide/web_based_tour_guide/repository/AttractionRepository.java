package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

    List<Attraction> findByDestinationDestinationId(Integer destinationId);

    List<Attraction> findByDestinationDestinationIdAndStatus(
            Integer destinationId,
            String status
    );

    List<Attraction> findByNameContainingIgnoreCase(String name);

    List<Attraction> findByStatus(String status);
}