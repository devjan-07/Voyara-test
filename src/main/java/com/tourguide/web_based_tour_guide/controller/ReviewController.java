package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Review;
import com.tourguide.web_based_tour_guide.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Review> createReview(
            @RequestBody Review review) {

        Review createdReview =
                reviewService.createReview(review);

        return new ResponseEntity<>(
                createdReview,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {

        return ResponseEntity.ok(
                reviewService.getAllReviews()
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                reviewService.getReviewById(id)
        );
    }

    // READ BY PACKAGE
    @GetMapping("/package/{packageId}")
    public ResponseEntity<List<Review>> getReviewsByPackage(
            @PathVariable Integer packageId) {

        return ResponseEntity.ok(
                reviewService.getReviewsByPackage(packageId)
        );
    }

    // READ BY USER
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUser(
            @PathVariable Integer userId) {

        return ResponseEntity.ok(
                reviewService.getReviewsByUser(userId)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Integer id,
            @RequestBody Review review) {

        return ResponseEntity.ok(
                reviewService.updateReview(id, review)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Integer id) {

        reviewService.deleteReview(id);

        return ResponseEntity.noContent().build();
    }
}