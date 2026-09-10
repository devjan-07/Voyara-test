package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.TourGuide;
import com.tourguide.web_based_tour_guide.service.TourGuideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/tour-guides")
public class TourGuideController {

    private final TourGuideService tourGuideService;

    public TourGuideController(TourGuideService tourGuideService) {
        this.tourGuideService = tourGuideService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TourGuide> createTourGuide(
            @RequestBody TourGuide tourGuide) {

        TourGuide createdGuide =
                tourGuideService.createTourGuide(tourGuide);

        return new ResponseEntity<>(createdGuide, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<TourGuide>> getAllTourGuides() {

        return ResponseEntity.ok(
                tourGuideService.getAllTourGuides()
        );
    }
    // SEARCH GUIDES BY MINIMUM RATING
    @GetMapping("/search")
    public ResponseEntity<List<TourGuide>> searchGuides(
            @RequestParam BigDecimal minRating) {

        return ResponseEntity.ok(
                tourGuideService.searchGuidesByRating(
                        minRating
                )
        );
    }

    // READ ONE
    @GetMapping("/{guideId}")
    public ResponseEntity<TourGuide> getTourGuideById(
            @PathVariable Integer guideId) {

        return ResponseEntity.ok(
                tourGuideService.getTourGuideById(guideId)
        );
    }

    // UPDATE
    @PutMapping("/{guideId}")
    public ResponseEntity<TourGuide> updateTourGuide(
            @PathVariable Integer guideId,
            @RequestBody TourGuide tourGuide) {

        return ResponseEntity.ok(
                tourGuideService.updateTourGuide(guideId, tourGuide)
        );
    }

    // DELETE
    @DeleteMapping("/{guideId}")
    public ResponseEntity<Void> deleteTourGuide(
            @PathVariable Integer guideId) {

        tourGuideService.deleteTourGuide(guideId);

        return ResponseEntity.noContent().build();
    }

    // SEARCH AVAILABLE GUIDES
    @GetMapping("/available")
    public ResponseEntity<List<TourGuide>> getAvailableTourGuides() {

        return ResponseEntity.ok(
                tourGuideService.getAvailableTourGuides()
        );
    }

}