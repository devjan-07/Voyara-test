package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByUserId(Integer userId);

    List<Booking> findByPackageId(Integer packageId);

    List<Booking> findByStatusIgnoreCase(String status);

    List<Booking> findByUserIdAndStatusIgnoreCase(
            Integer userId,
            String status
    );

    List<Booking> findByGuideId(Integer guideId);

    List<Booking> findByVehicleId(Integer vehicleId);

    List<Booking> findByRoomId(Integer roomId);

    @Query("""
    SELECT b FROM Booking b
    WHERE b.guideId = :guideId
      AND b.status <> 'CANCELLED'
      AND b.travelStartDate <= :endDate
      AND b.travelEndDate >= :startDate
""")
    List<Booking> findGuideConflictingBookings(
            Integer guideId,
            LocalDate startDate,
            LocalDate endDate
    );

    @Query("""
    SELECT b FROM Booking b
    WHERE b.vehicleId = :vehicleId
      AND b.status <> 'CANCELLED'
      AND b.travelStartDate <= :endDate
      AND b.travelEndDate >= :startDate
""")
    List<Booking> findVehicleConflictingBookings(
            Integer vehicleId,
            LocalDate startDate,
            LocalDate endDate
    );

    @Query("""
    SELECT b FROM Booking b
    WHERE b.roomId = :roomId
      AND b.status <> 'CANCELLED'
      AND b.travelStartDate <= :endDate
      AND b.travelEndDate >= :startDate
""")
    List<Booking> findRoomConflictingBookings(
            Integer roomId,
            LocalDate startDate,
            LocalDate endDate
    );
}