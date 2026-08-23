package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.GuideSpecialty;
import com.tourguide.web_based_tour_guide.repository.GuideSpecialtyRepository;
import com.tourguide.web_based_tour_guide.repository.TourGuideRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideSpecialtyService {

    private final GuideSpecialtyRepository guideSpecialtyRepository;
    private final TourGuideRepository tourGuideRepository;

    public GuideSpecialtyService(
            GuideSpecialtyRepository guideSpecialtyRepository,
            TourGuideRepository tourGuideRepository) {

        this.guideSpecialtyRepository = guideSpecialtyRepository;
        this.tourGuideRepository = tourGuideRepository;
    }

    // CREATE
    public GuideSpecialty createGuideSpecialty(
            GuideSpecialty guideSpecialty) {

        validateGuideSpecialty(guideSpecialty);

        validateGuideExists(guideSpecialty.getGuideId());

        if (guideSpecialtyRepository
                .existsByGuideIdAndSpecialtyIgnoreCase(
                        guideSpecialty.getGuideId(),
                        guideSpecialty.getSpecialty())) {

            throw new IllegalArgumentException(
                    "This specialty is already assigned to the guide."
            );
        }

        return guideSpecialtyRepository.save(guideSpecialty);
    }

    // READ ALL
    public List<GuideSpecialty> getAllGuideSpecialties() {
        return guideSpecialtyRepository.findAll();
    }

    // READ ONE
    public GuideSpecialty getGuideSpecialtyById(
            Integer guideSpecialtyId) {

        return guideSpecialtyRepository.findById(guideSpecialtyId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Guide specialty not found with ID: "
                                        + guideSpecialtyId
                        ));
    }

    // READ BY GUIDE
    public List<GuideSpecialty> getSpecialtiesByGuideId(
            Integer guideId) {

        validateGuideExists(guideId);

        return guideSpecialtyRepository.findByGuideId(guideId);
    }

    // READ BY SPECIALTY
    public List<GuideSpecialty> getSpecialtiesBySpecialty(
            String specialty) {

        return guideSpecialtyRepository
                .findBySpecialtyIgnoreCase(specialty);
    }

    // UPDATE
    public GuideSpecialty updateGuideSpecialty(
            Integer guideSpecialtyId,
            GuideSpecialty guideSpecialty) {

        GuideSpecialty existingSpecialty =
                getGuideSpecialtyById(guideSpecialtyId);

        validateGuideSpecialty(guideSpecialty);

        validateGuideExists(guideSpecialty.getGuideId());

        boolean duplicate =
                guideSpecialtyRepository
                        .existsByGuideIdAndSpecialtyIgnoreCase(
                                guideSpecialty.getGuideId(),
                                guideSpecialty.getSpecialty()
                        );

        if (duplicate &&
                !(existingSpecialty.getGuideId()
                        .equals(guideSpecialty.getGuideId())
                        &&
                        existingSpecialty.getSpecialty()
                                .equalsIgnoreCase(
                                        guideSpecialty.getSpecialty()
                                ))) {

            throw new IllegalArgumentException(
                    "This specialty is already assigned to the guide."
            );
        }

        existingSpecialty.setGuideId(
                guideSpecialty.getGuideId()
        );

        existingSpecialty.setSpecialty(
                guideSpecialty.getSpecialty()
        );

        return guideSpecialtyRepository.save(existingSpecialty);
    }

    // DELETE
    public void deleteGuideSpecialty(
            Integer guideSpecialtyId) {

        GuideSpecialty existingSpecialty =
                getGuideSpecialtyById(guideSpecialtyId);

        guideSpecialtyRepository.delete(existingSpecialty);
    }

    private void validateGuideExists(Integer guideId) {

        if (guideId == null) {
            throw new IllegalArgumentException(
                    "Guide ID is required"
            );
        }

        tourGuideRepository.findById(guideId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tour guide not found with ID: "
                                        + guideId
                        ));
    }

    private void validateGuideSpecialty(
            GuideSpecialty guideSpecialty) {

        if (guideSpecialty.getGuideId() == null) {
            throw new IllegalArgumentException(
                    "Guide ID is required"
            );
        }

        if (guideSpecialty.getSpecialty() == null ||
                guideSpecialty.getSpecialty().isBlank()) {

            throw new IllegalArgumentException(
                    "Specialty is required"
            );
        }
    }
}