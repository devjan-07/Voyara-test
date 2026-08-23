package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.GuideLanguage;
import com.tourguide.web_based_tour_guide.repository.GuideLanguageRepository;
import com.tourguide.web_based_tour_guide.repository.TourGuideRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideLanguageService {

    private final GuideLanguageRepository guideLanguageRepository;
    private final TourGuideRepository tourGuideRepository;

    public GuideLanguageService(
            GuideLanguageRepository guideLanguageRepository,
            TourGuideRepository tourGuideRepository) {

        this.guideLanguageRepository = guideLanguageRepository;
        this.tourGuideRepository = tourGuideRepository;
    }

    // CREATE
    public GuideLanguage createGuideLanguage(
            GuideLanguage guideLanguage) {

        validateGuideLanguage(guideLanguage);

        validateGuideExists(guideLanguage.getGuideId());

        if (guideLanguageRepository
                .existsByGuideIdAndLanguageIgnoreCase(
                        guideLanguage.getGuideId(),
                        guideLanguage.getLanguage())) {

            throw new IllegalArgumentException(
                    "This language is already assigned to the guide."
            );
        }

        return guideLanguageRepository.save(guideLanguage);
    }

    // READ ALL
    public List<GuideLanguage> getAllGuideLanguages() {
        return guideLanguageRepository.findAll();
    }

    // READ ONE
    public GuideLanguage getGuideLanguageById(
            Integer guideLanguageId) {

        return guideLanguageRepository.findById(guideLanguageId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Guide language not found with ID: "
                                        + guideLanguageId
                        ));
    }

    // READ BY GUIDE
    public List<GuideLanguage> getLanguagesByGuideId(
            Integer guideId) {

        validateGuideExists(guideId);

        return guideLanguageRepository.findByGuideId(guideId);
    }

    // READ BY LANGUAGE
    public List<GuideLanguage> getLanguagesByLanguage(
            String language) {

        return guideLanguageRepository
                .findByLanguageIgnoreCase(language);
    }

    // UPDATE
    public GuideLanguage updateGuideLanguage(
            Integer guideLanguageId,
            GuideLanguage guideLanguage) {

        GuideLanguage existingLanguage =
                getGuideLanguageById(guideLanguageId);

        validateGuideLanguage(guideLanguage);

        validateGuideExists(guideLanguage.getGuideId());

        boolean duplicate =
                guideLanguageRepository
                        .existsByGuideIdAndLanguageIgnoreCase(
                                guideLanguage.getGuideId(),
                                guideLanguage.getLanguage()
                        );

        if (duplicate &&
                !(existingLanguage.getGuideId()
                        .equals(guideLanguage.getGuideId())
                        &&
                        existingLanguage.getLanguage()
                                .equalsIgnoreCase(
                                        guideLanguage.getLanguage()
                                ))) {

            throw new IllegalArgumentException(
                    "This language is already assigned to the guide."
            );
        }

        existingLanguage.setGuideId(
                guideLanguage.getGuideId()
        );

        existingLanguage.setLanguage(
                guideLanguage.getLanguage()
        );

        return guideLanguageRepository.save(existingLanguage);
    }

    // DELETE
    public void deleteGuideLanguage(
            Integer guideLanguageId) {

        GuideLanguage existingLanguage =
                getGuideLanguageById(guideLanguageId);

        guideLanguageRepository.delete(existingLanguage);
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

    private void validateGuideLanguage(
            GuideLanguage guideLanguage) {

        if (guideLanguage.getGuideId() == null) {
            throw new IllegalArgumentException(
                    "Guide ID is required"
            );
        }

        if (guideLanguage.getLanguage() == null ||
                guideLanguage.getLanguage().isBlank()) {

            throw new IllegalArgumentException(
                    "Language is required"
            );
        }
    }
}