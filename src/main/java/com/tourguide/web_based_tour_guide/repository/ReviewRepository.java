package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository
        extends JpaRepository<Review, Integer> {

    List<Review> findByPackageId(Integer packageId);

    List<Review> findByUserId(Integer userId);

    boolean existsByUserIdAndPackageId(
            Integer userId,
            Integer packageId
    );
}