package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

    List<Accommodation> findByDestinationId(Integer destinationId);

    List<Accommodation> findByStatusIgnoreCase(String status);

    List<Accommodation> findByDestinationIdAndStatusIgnoreCase(
            Integer destinationId,
            String status
    );

    List<Accommodation> findByNameContainingIgnoreCase(String name);
}