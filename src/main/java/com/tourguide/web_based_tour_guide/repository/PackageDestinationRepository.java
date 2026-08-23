package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.PackageDestination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageDestinationRepository
        extends JpaRepository<PackageDestination, Integer> {

    List<PackageDestination> findByPackageId(Integer packageId);

    List<PackageDestination> findByDestinationId(Integer destinationId);

    boolean existsByPackageIdAndDestinationId(
            Integer packageId,
            Integer destinationId
    );
}