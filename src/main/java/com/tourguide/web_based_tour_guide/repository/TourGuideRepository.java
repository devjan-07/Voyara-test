package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourGuideRepository extends JpaRepository<TourGuide, Integer> {

    List<TourGuide> findByAvailabilityStatusIgnoreCase(
            String availabilityStatus
    );

    boolean existsByUserId(Integer userId);

    boolean existsByUserIdAndGuideIdNot(
            Integer userId,
            Integer guideId
    );
}