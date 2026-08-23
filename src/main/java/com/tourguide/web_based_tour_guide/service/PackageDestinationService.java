package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.PackageDestination;
import com.tourguide.web_based_tour_guide.repository.DestinationRepository;
import com.tourguide.web_based_tour_guide.repository.PackageDestinationRepository;
import com.tourguide.web_based_tour_guide.repository.TourPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageDestinationService {

    private final PackageDestinationRepository packageDestinationRepository;
    private final TourPackageRepository tourPackageRepository;
    private final DestinationRepository destinationRepository;

    public PackageDestinationService(
            PackageDestinationRepository packageDestinationRepository,
            TourPackageRepository tourPackageRepository,
            DestinationRepository destinationRepository) {

        this.packageDestinationRepository =
                packageDestinationRepository;

        this.tourPackageRepository =
                tourPackageRepository;

        this.destinationRepository =
                destinationRepository;
    }

    // CREATE
    public PackageDestination createPackageDestination(
            PackageDestination packageDestination) {

        validatePackageDestination(packageDestination);

        validatePackageExists(
                packageDestination.getPackageId()
        );

        validateDestinationExists(
                packageDestination.getDestinationId()
        );

        validateDuplicate(
                packageDestination.getPackageId(),
                packageDestination.getDestinationId(),
                null
        );

        return packageDestinationRepository.save(
                packageDestination
        );
    }

    // READ ALL
    public List<PackageDestination> getAllPackageDestinations() {

        return packageDestinationRepository.findAll();
    }

    // READ BY ID
    public PackageDestination getPackageDestinationById(
            Integer id) {

        return packageDestinationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Package destination not found with ID: "
                                        + id
                        ));
    }

    // READ BY PACKAGE
    public List<PackageDestination> getByPackageId(
            Integer packageId) {

        validatePackageExists(packageId);

        return packageDestinationRepository
                .findByPackageId(packageId);
    }

    // READ BY DESTINATION
    public List<PackageDestination> getByDestinationId(
            Integer destinationId) {

        validateDestinationExists(destinationId);

        return packageDestinationRepository
                .findByDestinationId(destinationId);
    }

    // UPDATE
    public PackageDestination updatePackageDestination(
            Integer id,
            PackageDestination packageDestination) {

        PackageDestination existing =
                getPackageDestinationById(id);

        validatePackageDestination(packageDestination);

        validatePackageExists(
                packageDestination.getPackageId()
        );

        validateDestinationExists(
                packageDestination.getDestinationId()
        );

        validateDuplicate(
                packageDestination.getPackageId(),
                packageDestination.getDestinationId(),
                id
        );

        existing.setPackageId(
                packageDestination.getPackageId()
        );

        existing.setDestinationId(
                packageDestination.getDestinationId()
        );

        existing.setSequenceNo(
                packageDestination.getSequenceNo()
        );

        existing.setDaysAllocated(
                packageDestination.getDaysAllocated()
        );

        return packageDestinationRepository.save(existing);
    }

    // DELETE
    public void deletePackageDestination(Integer id) {

        PackageDestination existing =
                getPackageDestinationById(id);

        packageDestinationRepository.delete(existing);
    }

    // VALIDATION
    private void validatePackageDestination(
            PackageDestination packageDestination) {

        if (packageDestination.getPackageId() == null) {

            throw new IllegalArgumentException(
                    "Package ID is required"
            );
        }

        if (packageDestination.getDestinationId() == null) {

            throw new IllegalArgumentException(
                    "Destination ID is required"
            );
        }

        if (packageDestination.getSequenceNo() == null ||
                packageDestination.getSequenceNo() <= 0) {

            throw new IllegalArgumentException(
                    "Sequence number must be greater than zero"
            );
        }

        if (packageDestination.getDaysAllocated() == null ||
                packageDestination.getDaysAllocated() <= 0) {

            throw new IllegalArgumentException(
                    "Days allocated must be greater than zero"
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

    // DESTINATION EXISTS
    private void validateDestinationExists(
            Integer destinationId) {

        destinationRepository.findById(destinationId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Destination not found with ID: "
                                        + destinationId
                        ));
    }

    // DUPLICATE CHECK
    private void validateDuplicate(
            Integer packageId,
            Integer destinationId,
            Integer currentId) {

        boolean duplicate =
                packageDestinationRepository
                        .existsByPackageIdAndDestinationId(
                                packageId,
                                destinationId
                        );

        if (!duplicate) {
            return;
        }

        // During UPDATE, allow the existing record itself.
        if (currentId != null) {

            PackageDestination existing =
                    packageDestinationRepository
                            .findById(currentId)
                            .orElse(null);

            if (existing != null &&
                    existing.getPackageId()
                            .equals(packageId) &&
                    existing.getDestinationId()
                            .equals(destinationId)) {

                return;
            }
        }

        throw new IllegalArgumentException(
                "This destination is already assigned to the package"
        );
    }
}