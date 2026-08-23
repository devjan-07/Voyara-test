package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Accommodation;
import com.tourguide.web_based_tour_guide.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @PostMapping
    public ResponseEntity<Accommodation> createAccommodation(
            @RequestBody Accommodation accommodation) {

        return ResponseEntity.ok(
                accommodationService.createAccommodation(accommodation)
        );
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> getAllAccommodations() {

        return ResponseEntity.ok(
                accommodationService.getAllAccommodations()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(
            @PathVariable Integer id) {

        return accommodationService.getAccommodationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<List<Accommodation>> getByDestination(
            @PathVariable Integer destinationId) {

        return ResponseEntity.ok(
                accommodationService.getAccommodationsByDestination(destinationId)
        );
    }

    @GetMapping("/active")
    public ResponseEntity<List<Accommodation>> getActiveAccommodations() {

        return ResponseEntity.ok(
                accommodationService.getActiveAccommodations()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<Accommodation>> searchAccommodations(
            @RequestParam String name) {

        return ResponseEntity.ok(
                accommodationService.searchAccommodations(name)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accommodation> updateAccommodation(
            @PathVariable Integer id,
            @RequestBody Accommodation accommodation) {

        return ResponseEntity.ok(
                accommodationService.updateAccommodation(id, accommodation)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(
            @PathVariable Integer id) {

        accommodationService.deleteAccommodation(id);

        return ResponseEntity.noContent().build();
    }
}