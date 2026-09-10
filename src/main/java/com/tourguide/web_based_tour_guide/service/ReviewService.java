package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Review;
import com.tourguide.web_based_tour_guide.repository.ReviewRepository;
import com.tourguide.web_based_tour_guide.repository.TourPackageRepository;
import com.tourguide.web_based_tour_guide.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final TourPackageRepository tourPackageRepository;

    public ReviewService(
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            TourPackageRepository tourPackageRepository) {

        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    // CREATE
    public Review createReview(Review review) {

        validateReview(review);

        // Check user exists
        userRepository.findById(review.getUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID: "
                                        + review.getUserId()
                        ));

        // Check package exists
        tourPackageRepository.findById(review.getPackageId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tour package not found with ID: "
                                        + review.getPackageId()
                        ));

        // Prevent duplicate review
        if (reviewRepository.existsByUserIdAndPackageId(
                review.getUserId(),
                review.getPackageId())) {

            throw new IllegalArgumentException(
                    "You have already reviewed this package"
            );
        }

        return reviewRepository.save(review);
    }

    // READ ALL
    public List<Review> getAllReviews() {

        return reviewRepository.findAll();
    }

    // READ BY ID
    public Review getReviewById(Integer id) {

        return reviewRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Review not found with ID: " + id
                        ));
    }

    // READ BY PACKAGE
    public List<Review> getReviewsByPackage(
            Integer packageId) {

        return reviewRepository.findByPackageId(packageId);
    }

    // READ BY USER
    public List<Review> getReviewsByUser(
            Integer userId) {

        return reviewRepository.findByUserId(userId);
    }

    // UPDATE
    public Review updateReview(
            Integer id,
            Review review) {

        Review existingReview =
                getReviewById(id);

        validateReview(review);

        existingReview.setRating(
                review.getRating()
        );

        existingReview.setComment(
                review.getComment()
        );

        return reviewRepository.save(existingReview);
    }

    // DELETE
    public void deleteReview(Integer id) {

        Review existingReview =
                getReviewById(id);

        reviewRepository.delete(existingReview);
    }

    // VALIDATION
    private void validateReview(Review review) {

        if (review.getUserId() == null) {

            throw new IllegalArgumentException(
                    "User ID is required"
            );
        }

        if (review.getPackageId() == null) {

            throw new IllegalArgumentException(
                    "Package ID is required"
            );
        }

        if (review.getRating() == null ||
                review.getRating()
                        .compareTo(BigDecimal.ONE) < 0 ||
                review.getRating()
                        .compareTo(new BigDecimal("5.0")) > 0) {

            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5"
            );
        }

        if (review.getComment() == null ||
                review.getComment().isBlank()) {

            throw new IllegalArgumentException(
                    "Comment is required"
            );
        }
    }
}