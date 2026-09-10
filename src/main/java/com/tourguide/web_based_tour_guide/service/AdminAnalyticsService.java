package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.repository.AccommodationRepository;
import com.tourguide.web_based_tour_guide.repository.BookingRepository;
import com.tourguide.web_based_tour_guide.repository.ReviewRepository;
import com.tourguide.web_based_tour_guide.repository.TourGuideRepository;
import com.tourguide.web_based_tour_guide.repository.TourPackageRepository;
import com.tourguide.web_based_tour_guide.repository.UserRepository;
import com.tourguide.web_based_tour_guide.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AdminAnalyticsService {

    private final UserRepository userRepository;
    private final TourPackageRepository tourPackageRepository;
    private final BookingRepository bookingRepository;
    private final TourGuideRepository tourGuideRepository;
    private final ReviewRepository reviewRepository;
    private final VehicleRepository vehicleRepository;
    private final AccommodationRepository accommodationRepository;

    public AdminAnalyticsService(
            UserRepository userRepository,
            TourPackageRepository tourPackageRepository,
            BookingRepository bookingRepository,
            TourGuideRepository tourGuideRepository,
            ReviewRepository reviewRepository,
            VehicleRepository vehicleRepository,
            AccommodationRepository accommodationRepository) {

        this.userRepository = userRepository;
        this.tourPackageRepository = tourPackageRepository;
        this.bookingRepository = bookingRepository;
        this.tourGuideRepository = tourGuideRepository;
        this.reviewRepository = reviewRepository;
        this.vehicleRepository = vehicleRepository;
        this.accommodationRepository = accommodationRepository;
    }

    public Map<String, Long> getAnalytics() {

        Map<String, Long> analytics = new LinkedHashMap<>();

        analytics.put(
                "totalUsers",
                userRepository.count()
        );

        analytics.put(
                "totalPackages",
                tourPackageRepository.count()
        );

        analytics.put(
                "totalBookings",
                bookingRepository.count()
        );

        analytics.put(
                "totalGuides",
                tourGuideRepository.count()
        );

        analytics.put(
                "totalReviews",
                reviewRepository.count()
        );

        analytics.put(
                "totalVehicles",
                vehicleRepository.count()
        );

        analytics.put(
                "totalAccommodations",
                accommodationRepository.count()
        );

        analytics.put(
                "confirmedBookings",
                (long) bookingRepository
                        .findByStatusIgnoreCase("CONFIRMED")
                        .size()
        );

        analytics.put(
                "cancelledBookings",
                (long) bookingRepository
                        .findByStatusIgnoreCase("CANCELLED")
                        .size()
        );

        return analytics;
    }
}
