package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findByAccommodationId(Integer accommodationId);

    List<Room> findByAvailabilityStatusIgnoreCase(String availabilityStatus);

    List<Room> findByStatusIgnoreCase(String status);

    List<Room> findByAccommodationIdAndAvailabilityStatusIgnoreCase(
            Integer accommodationId,
            String availabilityStatus
    );

    List<Room> findByRoomTypeContainingIgnoreCase(String roomType);
}