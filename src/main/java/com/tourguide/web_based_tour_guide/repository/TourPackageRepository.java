package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourPackageRepository extends JpaRepository<TourPackage, Integer> {

    List<TourPackage> findByStatusIgnoreCase(String status);

    List<TourPackage> findByCreatedBy(Integer createdBy);

    List<TourPackage> findByPackageNameContainingIgnoreCase(String packageName);

    List<TourPackage> findByDurationDays(Integer durationDays);
}