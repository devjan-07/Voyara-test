package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.TourPackage;
import com.tourguide.web_based_tour_guide.repository.TourPackageRepository;
import com.tourguide.web_based_tour_guide.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TourPackageService {

    private final TourPackageRepository tourPackageRepository;
    private final UserRepository userRepository;

    public TourPackageService(
            TourPackageRepository tourPackageRepository,
            UserRepository userRepository) {

        this.tourPackageRepository = tourPackageRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public TourPackage createTourPackage(
            TourPackage tourPackage) {

        validateTourPackage(tourPackage);
        validateCreator(tourPackage.getCreatedBy());

        if (tourPackage.getStatus() == null ||
                tourPackage.getStatus().isBlank()) {

            tourPackage.setStatus("ACTIVE");
        }

        return tourPackageRepository.save(tourPackage);
    }

    // READ ALL
    public List<TourPackage> getAllTourPackages() {

        return tourPackageRepository.findAll();
    }

    // READ BY ID
    public Optional<TourPackage> getTourPackageById(
            Integer id) {

        return tourPackageRepository.findById(id);
    }

    // READ ACTIVE
    public List<TourPackage> getActiveTourPackages() {

        return tourPackageRepository
                .findByStatusIgnoreCase("ACTIVE");
    }

    // READ BY CREATOR
    public List<TourPackage> getTourPackagesByCreator(
            Integer createdBy) {

        validateCreator(createdBy);

        return tourPackageRepository
                .findByCreatedBy(createdBy);
    }

    // SEARCH
    public List<TourPackage> searchTourPackages(
            String packageName) {

        return tourPackageRepository
                .findByPackageNameContainingIgnoreCase(
                        packageName
                );
    }

    // READ BY DURATION
    public List<TourPackage> getTourPackagesByDuration(
            Integer durationDays) {

        return tourPackageRepository
                .findByDurationDays(durationDays);
    }

    // UPDATE
    public TourPackage updateTourPackage(
            Integer id,
            TourPackage tourPackage) {

        TourPackage existingPackage =
                tourPackageRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Tour package not found with ID: "
                                                + id
                                ));

        validateTourPackage(tourPackage);
        validateCreator(tourPackage.getCreatedBy());

        existingPackage.setPackageName(
                tourPackage.getPackageName()
        );

        existingPackage.setDescription(
                tourPackage.getDescription()
        );

        existingPackage.setDurationDays(
                tourPackage.getDurationDays()
        );

        existingPackage.setBasePrice(
                tourPackage.getBasePrice()
        );

        existingPackage.setCreatedBy(
                tourPackage.getCreatedBy()
        );

        existingPackage.setStatus(
                tourPackage.getStatus()
        );

        return tourPackageRepository.save(existingPackage);
    }

    // DELETE
    public void deleteTourPackage(Integer id) {

        TourPackage existingPackage =
                tourPackageRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Tour package not found with ID: "
                                                + id
                                ));

        tourPackageRepository.delete(existingPackage);
    }

    // VALIDATION
    private void validateTourPackage(
            TourPackage tourPackage) {

        if (tourPackage.getPackageName() == null ||
                tourPackage.getPackageName().isBlank()) {

            throw new IllegalArgumentException(
                    "Package name is required"
            );
        }

        if (tourPackage.getDurationDays() == null ||
                tourPackage.getDurationDays() <= 0) {

            throw new IllegalArgumentException(
                    "Duration must be greater than zero"
            );
        }

        if (tourPackage.getBasePrice() == null ||
                tourPackage.getBasePrice()
                        .compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Base price cannot be negative"
            );
        }

        if (tourPackage.getStatus() != null &&
                !tourPackage.getStatus()
                        .equalsIgnoreCase("ACTIVE") &&
                !tourPackage.getStatus()
                        .equalsIgnoreCase("INACTIVE")) {

            throw new IllegalArgumentException(
                    "Invalid tour package status"
            );
        }
    }

    // CREATOR VALIDATION
    private void validateCreator(Integer createdBy) {

        if (createdBy == null) {

            throw new IllegalArgumentException(
                    "Creator user ID is required"
            );
        }

        userRepository.findById(createdBy)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Creator user not found with ID: "
                                        + createdBy
                        ));
    }
}