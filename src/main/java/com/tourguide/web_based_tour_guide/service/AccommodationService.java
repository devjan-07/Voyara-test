package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Accommodation;
import com.tourguide.web_based_tour_guide.repository.AccommodationRepository;
import org.springframework.stereotype.Service;
import com.tourguide.web_based_tour_guide.repository.DestinationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final DestinationRepository destinationRepository;

    public AccommodationService(
            AccommodationRepository accommodationRepository,
            DestinationRepository destinationRepository) {

        this.accommodationRepository = accommodationRepository;
        this.destinationRepository = destinationRepository;
    }

    public Accommodation createAccommodation(
            Accommodation accommodation) {

        validateAccommodation(accommodation);

        destinationRepository.findById(
                accommodation.getDestinationId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Destination not found with ID: "
                                + accommodation.getDestinationId()
                ));

        if (accommodation.getStatus() == null
                || accommodation.getStatus().isBlank()) {

            accommodation.setStatus("ACTIVE");
        }

        return accommodationRepository.save(accommodation);
    }
    private void validateAccommodation(
            Accommodation accommodation) {

        if (accommodation.getDestinationId() == null) {

            throw new IllegalArgumentException(
                    "Destination ID is required"
            );
        }

        if (accommodation.getName() == null ||
                accommodation.getName().isBlank()) {

            throw new IllegalArgumentException(
                    "Accommodation name is required"
            );
        }

        if (accommodation.getAddress() == null ||
                accommodation.getAddress().isBlank()) {

            throw new IllegalArgumentException(
                    "Accommodation address is required"
            );
        }

        if (accommodation.getStatus() != null &&
                !accommodation.getStatus()
                        .equalsIgnoreCase("ACTIVE") &&
                !accommodation.getStatus()
                        .equalsIgnoreCase("INACTIVE")) {

            throw new IllegalArgumentException(
                    "Invalid accommodation status"
            );
        }
    }

    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    public Optional<Accommodation> getAccommodationById(
            Integer id) {

        return accommodationRepository.findById(id);
    }

    public List<Accommodation> getAccommodationsByDestination(
            Integer destinationId) {

        return accommodationRepository
                .findByDestinationId(destinationId);
    }

    public List<Accommodation> getActiveAccommodations() {

        return accommodationRepository
                .findByStatusIgnoreCase("ACTIVE");
    }

    public List<Accommodation> searchAccommodations(
            String name) {

        return accommodationRepository
                .findByNameContainingIgnoreCase(name);
    }

    public Accommodation updateAccommodation(
            Integer id,
            Accommodation accommodation) {

        Accommodation existing =
                accommodationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Accommodation not found with ID: "
                                                + id
                                ));

        existing.setDestinationId(
                accommodation.getDestinationId()
        );

        existing.setName(
                accommodation.getName()
        );

        existing.setAddress(
                accommodation.getAddress()
        );

        existing.setContactNumber(
                accommodation.getContactNumber()
        );

        existing.setDescription(
                accommodation.getDescription()
        );

        existing.setFacilities(
                accommodation.getFacilities()
        );

        existing.setStatus(
                accommodation.getStatus()
        );

        return accommodationRepository.save(existing);
    }

    public void deleteAccommodation(Integer id) {

        Accommodation existing =
                accommodationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Accommodation not found with ID: "
                                                + id
                                ));

        accommodationRepository.delete(existing);
    }
}