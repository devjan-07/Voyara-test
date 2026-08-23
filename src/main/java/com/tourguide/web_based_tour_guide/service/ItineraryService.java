package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Itinerary;
import com.tourguide.web_based_tour_guide.repository.ItineraryRepository;
import com.tourguide.web_based_tour_guide.repository.TourPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final TourPackageRepository tourPackageRepository;

    public ItineraryService(
            ItineraryRepository itineraryRepository,
            TourPackageRepository tourPackageRepository) {

        this.itineraryRepository = itineraryRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    // CREATE
    public Itinerary createItinerary(Itinerary itinerary) {

        validateItinerary(itinerary);

        validatePackageExists(
                itinerary.getPackageId()
        );

        validateDuplicate(
                itinerary.getPackageId(),
                itinerary.getDayNumber(),
                null
        );

        return itineraryRepository.save(itinerary);
    }

    // READ ALL
    public List<Itinerary> getAllItineraries() {

        return itineraryRepository.findAll();
    }

    // READ BY ID
    public Itinerary getItineraryById(Integer id) {

        return itineraryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Itinerary not found with ID: " + id
                        ));
    }

    // READ BY PACKAGE
    public List<Itinerary> getItinerariesByPackageId(
            Integer packageId) {

        validatePackageExists(packageId);

        return itineraryRepository.findByPackageId(packageId);
    }

    // UPDATE
    public Itinerary updateItinerary(
            Integer id,
            Itinerary itinerary) {

        Itinerary existing =
                getItineraryById(id);

        validateItinerary(itinerary);

        validatePackageExists(
                itinerary.getPackageId()
        );

        validateDuplicate(
                itinerary.getPackageId(),
                itinerary.getDayNumber(),
                id
        );

        existing.setPackageId(
                itinerary.getPackageId()
        );

        existing.setDayNumber(
                itinerary.getDayNumber()
        );

        existing.setTitle(
                itinerary.getTitle()
        );

        existing.setDescription(
                itinerary.getDescription()
        );

        return itineraryRepository.save(existing);
    }

    // DELETE
    public void deleteItinerary(Integer id) {

        Itinerary existing =
                getItineraryById(id);

        itineraryRepository.delete(existing);
    }

    // VALIDATION
    private void validateItinerary(
            Itinerary itinerary) {

        if (itinerary.getPackageId() == null) {

            throw new IllegalArgumentException(
                    "Package ID is required"
            );
        }

        if (itinerary.getDayNumber() == null ||
                itinerary.getDayNumber() <= 0) {

            throw new IllegalArgumentException(
                    "Day number must be greater than zero"
            );
        }

        if (itinerary.getTitle() == null ||
                itinerary.getTitle().isBlank()) {

            throw new IllegalArgumentException(
                    "Itinerary title is required"
            );
        }
    }

    // PACKAGE EXISTS
    private void validatePackageExists(
            Integer packageId) {

        tourPackageRepository.findById(packageId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tour package not found with ID: "
                                        + packageId
                        ));
    }

    // DUPLICATE PACKAGE + DAY CHECK
    private void validateDuplicate(
            Integer packageId,
            Integer dayNumber,
            Integer currentId) {

        boolean duplicate =
                itineraryRepository
                        .existsByPackageIdAndDayNumber(
                                packageId,
                                dayNumber
                        );

        if (!duplicate) {
            return;
        }

        // During update, allow the current record itself.
        if (currentId != null) {

            Itinerary existing =
                    itineraryRepository
                            .findById(currentId)
                            .orElse(null);

            if (existing != null &&
                    existing.getPackageId()
                            .equals(packageId) &&
                    existing.getDayNumber()
                            .equals(dayNumber)) {

                return;
            }
        }

        throw new IllegalArgumentException(
                "An itinerary already exists for this package and day"
        );
    }
}