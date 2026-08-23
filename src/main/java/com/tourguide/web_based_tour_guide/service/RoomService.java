package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Room;
import com.tourguide.web_based_tour_guide.repository.AccommodationRepository;
import com.tourguide.web_based_tour_guide.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final AccommodationRepository accommodationRepository;

    public RoomService(
            RoomRepository roomRepository,
            AccommodationRepository accommodationRepository) {

        this.roomRepository = roomRepository;
        this.accommodationRepository = accommodationRepository;
    }

    // CREATE
    public Room createRoom(Room room) {

        validateRoom(room);

        accommodationRepository.findById(
                room.getAccommodationId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Accommodation not found with ID: "
                                + room.getAccommodationId()
                ));

        if (room.getAvailabilityStatus() == null ||
                room.getAvailabilityStatus().isBlank()) {

            room.setAvailabilityStatus("AVAILABLE");
        }

        if (room.getStatus() == null ||
                room.getStatus().isBlank()) {

            room.setStatus("ACTIVE");
        }

        return roomRepository.save(room);
    }

    // READ ALL
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // READ ONE
    public Optional<Room> getRoomById(Integer id) {
        return roomRepository.findById(id);
    }

    // READ BY ACCOMMODATION
    public List<Room> getRoomsByAccommodation(
            Integer accommodationId) {

        return roomRepository.findByAccommodationId(
                accommodationId
        );
    }

    // AVAILABLE ROOMS
    public List<Room> getAvailableRooms() {

        return roomRepository
                .findByAvailabilityStatusIgnoreCase(
                        "AVAILABLE"
                );
    }

    // AVAILABLE ROOMS BY ACCOMMODATION
    public List<Room> getAvailableRoomsByAccommodation(
            Integer accommodationId) {

        return roomRepository
                .findByAccommodationIdAndAvailabilityStatusIgnoreCase(
                        accommodationId,
                        "AVAILABLE"
                );
    }

    // SEARCH
    public List<Room> searchRoomsByType(
            String roomType) {

        return roomRepository
                .findByRoomTypeContainingIgnoreCase(roomType);
    }

    // UPDATE
    public Room updateRoom(
            Integer id,
            Room room) {

        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found with ID: " + id
                        )
                );

        validateRoom(room);

        accommodationRepository.findById(
                room.getAccommodationId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Accommodation not found with ID: "
                                + room.getAccommodationId()
                ));

        existingRoom.setAccommodationId(
                room.getAccommodationId()
        );

        existingRoom.setRoomType(
                room.getRoomType()
        );

        existingRoom.setCapacity(
                room.getCapacity()
        );

        existingRoom.setPricePerNight(
                room.getPricePerNight()
        );

        existingRoom.setAvailabilityStatus(
                room.getAvailabilityStatus()
        );

        existingRoom.setStatus(
                room.getStatus()
        );

        return roomRepository.save(existingRoom);
    }

    // DELETE
    public void deleteRoom(Integer id) {

        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found with ID: " + id
                        )
                );

        roomRepository.delete(existingRoom);
    }

    // VALIDATION
    private void validateRoom(Room room) {

        if (room.getAccommodationId() == null) {

            throw new IllegalArgumentException(
                    "Accommodation ID is required"
            );
        }

        if (room.getRoomType() == null ||
                room.getRoomType().isBlank()) {

            throw new IllegalArgumentException(
                    "Room type is required"
            );
        }

        if (room.getCapacity() == null ||
                room.getCapacity() <= 0) {

            throw new IllegalArgumentException(
                    "Room capacity must be greater than zero"
            );
        }

        if (room.getPricePerNight() == null ||
                room.getPricePerNight()
                        .compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Room price cannot be negative"
            );
        }

        if (room.getAvailabilityStatus() != null &&
                !room.getAvailabilityStatus()
                        .equalsIgnoreCase("AVAILABLE") &&
                !room.getAvailabilityStatus()
                        .equalsIgnoreCase("UNAVAILABLE")) {

            throw new IllegalArgumentException(
                    "Invalid room availability status"
            );
        }

        if (room.getStatus() != null &&
                !room.getStatus()
                        .equalsIgnoreCase("ACTIVE") &&
                !room.getStatus()
                        .equalsIgnoreCase("INACTIVE")) {

            throw new IllegalArgumentException(
                    "Invalid room status"
            );
        }
    }
}