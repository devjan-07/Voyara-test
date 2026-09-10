package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Destination;
import com.tourguide.web_based_tour_guide.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Destination> createDestination(
            @RequestBody Destination destination) {

        return new ResponseEntity<>(
                destinationService.createDestination(destination),
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {

        return ResponseEntity.ok(
                destinationService.getAllDestinations()
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                destinationService.getDestinationById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Destination> updateDestination(
            @PathVariable Integer id,
            @RequestBody Destination destination) {

        return ResponseEntity.ok(
                destinationService.updateDestination(
                        id,
                        destination
                )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(
            @PathVariable Integer id) {

        destinationService.deleteDestination(id);

        return ResponseEntity.noContent().build();
    }

    // SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<Destination>> searchDestinations(
            @RequestParam(required = false) String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {

            return ResponseEntity.ok(
                    destinationService.getAllDestinations()
            );
        }

        return ResponseEntity.ok(
                destinationService.searchDestinations(keyword)
        );
    }

    // FILTER BY CATEGORY
    @GetMapping("/category")
    public ResponseEntity<List<Destination>> filterByCategory(
            @RequestParam String category) {

        return ResponseEntity.ok(
                destinationService.getDestinationsByCategory(category)
        );
    }
}