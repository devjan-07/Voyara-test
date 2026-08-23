package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Itinerary;
import com.tourguide.web_based_tour_guide.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(
            ItineraryService itineraryService) {

        this.itineraryService = itineraryService;
    }

    @PostMapping
    public ResponseEntity<Itinerary> create(
            @RequestBody Itinerary itinerary) {

        Itinerary created =
                itineraryService.createItinerary(itinerary);

        return new ResponseEntity<>(
                created,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Itinerary>> getAll() {

        return ResponseEntity.ok(
                itineraryService.getAllItineraries()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itinerary> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                itineraryService.getItineraryById(id)
        );
    }

    @GetMapping("/package/{packageId}")
    public ResponseEntity<List<Itinerary>> getByPackageId(
            @PathVariable Integer packageId) {

        return ResponseEntity.ok(
                itineraryService
                        .getItinerariesByPackageId(packageId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Itinerary> update(
            @PathVariable Integer id,
            @RequestBody Itinerary itinerary) {

        return ResponseEntity.ok(
                itineraryService
                        .updateItinerary(id, itinerary)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        itineraryService.deleteItinerary(id);

        return ResponseEntity.noContent().build();
    }
}