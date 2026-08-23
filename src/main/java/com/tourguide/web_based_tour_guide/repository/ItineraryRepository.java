package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository
        extends JpaRepository<Itinerary, Integer> {

    List<Itinerary> findByPackageIdOrderByDayNumberAsc(Integer packageId);
    List<Itinerary> findByPackageId(Integer packageId);
    boolean existsByPackageIdAndDayNumber(
            Integer packageId,
            Integer dayNumber
    );
}