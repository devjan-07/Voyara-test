package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.TourGuide;
import com.tourguide.web_based_tour_guide.repository.TourGuideRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TourGuideService {

    private final TourGuideRepository tourGuideRepository;

    public TourGuideService(TourGuideRepository tourGuideRepository) {
        this.tourGuideRepository = tourGuideRepository;
    }

    // CREATE
    public TourGuide createTourGuide(TourGuide tourGuide) {

        validateTourGuide(tourGuide);

        if (tourGuideRepository.existsByUserId(
                tourGuide.getUserId())) {

            throw new IllegalArgumentException(
                    "This user is already registered as a tour guide"
            );
        }

        if (tourGuide.getAvailabilityStatus() == null ||
                tourGuide.getAvailabilityStatus().isBlank()) {

            tourGuide.setAvailabilityStatus("AVAILABLE");
        }

        if (tourGuide.getStatus() == null ||
                tourGuide.getStatus().isBlank()) {

            tourGuide.setStatus("ACTIVE");
        }

        return tourGuideRepository.save(tourGuide);
    }

    // READ ALL
    public List<TourGuide> getAllTourGuides() {
        return tourGuideRepository.findAll();
    }

    // READ ONE
    public TourGuide getTourGuideById(Integer guideId) {

        return tourGuideRepository.findById(guideId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tour guide not found with ID: "
                                        + guideId
                        ));
    }

    // UPDATE
    public TourGuide updateTourGuide(
            Integer guideId,
            TourGuide tourGuide) {

        TourGuide existingGuide =
                getTourGuideById(guideId);

        validateTourGuide(tourGuide);

        if (tourGuideRepository.existsByUserIdAndGuideIdNot(
                tourGuide.getUserId(),
                guideId)) {

            throw new IllegalArgumentException(
                    "This user is already assigned to another tour guide"
            );
        }

        existingGuide.setUserId(
                tourGuide.getUserId()
        );

        existingGuide.setExperienceYears(
                tourGuide.getExperienceYears()
        );

        existingGuide.setBio(
                tourGuide.getBio()
        );

        existingGuide.setAvailabilityStatus(
                tourGuide.getAvailabilityStatus()
        );

        existingGuide.setRating(
                tourGuide.getRating()
        );

        existingGuide.setStatus(
                tourGuide.getStatus()
        );

        return tourGuideRepository.save(existingGuide);
    }

    // DELETE
    public void deleteTourGuide(Integer guideId) {

        TourGuide existingGuide =
                getTourGuideById(guideId);

        tourGuideRepository.delete(existingGuide);
    }

    // GET AVAILABLE GUIDES
    public List<TourGuide> getAvailableTourGuides() {

        return tourGuideRepository
                .findByAvailabilityStatusIgnoreCase(
                        "AVAILABLE"
                );
    }

    // VALIDATION
    private void validateTourGuide(TourGuide tourGuide) {

        if (tourGuide.getUserId() == null) {

            throw new IllegalArgumentException(
                    "User ID is required"
            );
        }

        if (tourGuide.getExperienceYears() == null ||
                tourGuide.getExperienceYears() < 0) {

            throw new IllegalArgumentException(
                    "Experience years cannot be negative"
            );
        }

        if (tourGuide.getRating() == null) {

            tourGuide.setRating(BigDecimal.ZERO);
        }

        if (tourGuide.getRating()
                .compareTo(BigDecimal.ZERO) < 0 ||
                tourGuide.getRating()
                        .compareTo(new BigDecimal("5.00")) > 0) {

            throw new IllegalArgumentException(
                    "Rating must be between 0 and 5"
            );
        }

        if (tourGuide.getAvailabilityStatus() != null &&
                !isValidAvailabilityStatus(
                        tourGuide.getAvailabilityStatus())) {

            throw new IllegalArgumentException(
                    "Invalid availability status"
            );
        }

        if (tourGuide.getStatus() != null &&
                !isValidStatus(tourGuide.getStatus())) {

            throw new IllegalArgumentException(
                    "Invalid guide status"
            );
        }
    }

    private boolean isValidAvailabilityStatus(
            String status) {

        return status.equalsIgnoreCase("AVAILABLE") ||
                status.equalsIgnoreCase("UNAVAILABLE");
    }

    private boolean isValidStatus(String status) {

        return status.equalsIgnoreCase("ACTIVE") ||
                status.equalsIgnoreCase("INACTIVE");
    }
}