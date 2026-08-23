package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.TourPackage;
import com.tourguide.web_based_tour_guide.service.TourPackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour-packages")
public class TourPackageController {

    private final TourPackageService tourPackageService;

    public TourPackageController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @PostMapping
    public ResponseEntity<TourPackage> createTourPackage(
            @RequestBody TourPackage tourPackage) {

        return ResponseEntity.ok(
                tourPackageService.createTourPackage(tourPackage)
        );
    }

    @GetMapping
    public ResponseEntity<List<TourPackage>> getAllTourPackages() {

        return ResponseEntity.ok(
                tourPackageService.getAllTourPackages()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourPackage> getTourPackageById(
            @PathVariable Integer id) {

        return tourPackageService.getTourPackageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/active")
    public ResponseEntity<List<TourPackage>> getActiveTourPackages() {

        return ResponseEntity.ok(
                tourPackageService.getActiveTourPackages()
        );
    }

    @GetMapping("/creator/{createdBy}")
    public ResponseEntity<List<TourPackage>> getTourPackagesByCreator(
            @PathVariable Integer createdBy) {

        return ResponseEntity.ok(
                tourPackageService.getTourPackagesByCreator(createdBy)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<TourPackage>> searchTourPackages(
            @RequestParam String name) {

        return ResponseEntity.ok(
                tourPackageService.searchTourPackages(name)
        );
    }

    @GetMapping("/duration/{durationDays}")
    public ResponseEntity<List<TourPackage>> getTourPackagesByDuration(
            @PathVariable Integer durationDays) {

        return ResponseEntity.ok(
                tourPackageService.getTourPackagesByDuration(durationDays)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourPackage> updateTourPackage(
            @PathVariable Integer id,
            @RequestBody TourPackage tourPackage) {

        return ResponseEntity.ok(
                tourPackageService.updateTourPackage(id, tourPackage)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTourPackage(
            @PathVariable Integer id) {

        tourPackageService.deleteTourPackage(id);

        return ResponseEntity.noContent().build();
    }
}