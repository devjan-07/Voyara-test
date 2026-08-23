package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.PackageDestination;
import com.tourguide.web_based_tour_guide.service.PackageDestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/package-destinations")
public class PackageDestinationController {

    private final PackageDestinationService packageDestinationService;

    public PackageDestinationController(
            PackageDestinationService packageDestinationService) {
        this.packageDestinationService = packageDestinationService;
    }

    @PostMapping
    public ResponseEntity<PackageDestination> create(
            @RequestBody PackageDestination packageDestination) {

        PackageDestination created =
                packageDestinationService
                        .createPackageDestination(packageDestination);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PackageDestination>> getAll() {

        return ResponseEntity.ok(
                packageDestinationService
                        .getAllPackageDestinations()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageDestination> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                packageDestinationService
                        .getPackageDestinationById(id)
        );
    }

    @GetMapping("/package/{packageId}")
    public ResponseEntity<List<PackageDestination>> getByPackageId(
            @PathVariable Integer packageId) {

        return ResponseEntity.ok(
                packageDestinationService
                        .getByPackageId(packageId)
        );
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<List<PackageDestination>> getByDestinationId(
            @PathVariable Integer destinationId) {

        return ResponseEntity.ok(
                packageDestinationService
                        .getByDestinationId(destinationId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageDestination> update(
            @PathVariable Integer id,
            @RequestBody PackageDestination packageDestination) {

        return ResponseEntity.ok(
                packageDestinationService
                        .updatePackageDestination(
                                id,
                                packageDestination
                        )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        packageDestinationService
                .deletePackageDestination(id);

        return ResponseEntity.noContent().build();
    }
}