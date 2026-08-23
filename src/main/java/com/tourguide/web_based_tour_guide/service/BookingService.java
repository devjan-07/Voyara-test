package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Booking;
import com.tourguide.web_based_tour_guide.entity.Room;
import com.tourguide.web_based_tour_guide.entity.Vehicle;
import com.tourguide.web_based_tour_guide.repository.BookingRepository;
import com.tourguide.web_based_tour_guide.repository.RoomRepository;
import com.tourguide.web_based_tour_guide.repository.TourGuideRepository;
import com.tourguide.web_based_tour_guide.repository.TourPackageRepository;
import com.tourguide.web_based_tour_guide.repository.UserRepository;
import com.tourguide.web_based_tour_guide.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourPackageRepository tourPackageRepository;
    private final TourGuideRepository tourGuideRepository;
    private final VehicleRepository vehicleRepository;
    private final RoomRepository roomRepository;

    public BookingService(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            TourPackageRepository tourPackageRepository,
            TourGuideRepository tourGuideRepository,
            VehicleRepository vehicleRepository,
            RoomRepository roomRepository) {

        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tourPackageRepository = tourPackageRepository;
        this.tourGuideRepository = tourGuideRepository;
        this.vehicleRepository = vehicleRepository;
        this.roomRepository = roomRepository;
    }

    // CREATE
    public Booking createBooking(Booking booking) {

        validateBooking(booking);
        validateRelationships(booking);
        validateResourceConflicts(booking, null);

        if (booking.getStatus() == null ||
                booking.getStatus().isBlank()) {

            booking.setStatus("PENDING");
        }

        return bookingRepository.save(booking);
    }

    // READ ALL
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // READ BY ID
    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    // READ BY USER
    public List<Booking> getBookingsByUser(Integer userId) {
        return bookingRepository.findByUserId(userId);
    }

    // READ BY PACKAGE
    public List<Booking> getBookingsByPackage(Integer packageId) {
        return bookingRepository.findByPackageId(packageId);
    }

    // READ BY STATUS
    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatusIgnoreCase(status);
    }

    // READ BY GUIDE
    public List<Booking> getBookingsByGuide(Integer guideId) {
        return bookingRepository.findByGuideId(guideId);
    }

    // READ BY VEHICLE
    public List<Booking> getBookingsByVehicle(Integer vehicleId) {
        return bookingRepository.findByVehicleId(vehicleId);
    }

    // READ BY ROOM
    public List<Booking> getBookingsByRoom(Integer roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    // UPDATE
    public Booking updateBooking(
            Integer id,
            Booking booking) {

        Booking existingBooking =
                bookingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found with ID: "
                                                + id
                                )
                        );

        validateBooking(booking);
        validateRelationships(booking);
        validateResourceConflicts(booking, id);

        existingBooking.setUserId(
                booking.getUserId()
        );

        existingBooking.setPackageId(
                booking.getPackageId()
        );

        existingBooking.setGuideId(
                booking.getGuideId()
        );

        existingBooking.setVehicleId(
                booking.getVehicleId()
        );

        existingBooking.setRoomId(
                booking.getRoomId()
        );

        existingBooking.setTravelStartDate(
                booking.getTravelStartDate()
        );

        existingBooking.setTravelEndDate(
                booking.getTravelEndDate()
        );

        existingBooking.setNumberOfTravellers(
                booking.getNumberOfTravellers()
        );

        existingBooking.setTotalAmount(
                booking.getTotalAmount()
        );

        existingBooking.setStatus(
                booking.getStatus()
        );

        return bookingRepository.save(existingBooking);
    }

    // CANCEL
    public Booking cancelBooking(Integer id) {

        Booking booking =
                bookingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found with ID: "
                                                + id
                                )
                        );

        if ("CANCELLED".equalsIgnoreCase(
                booking.getStatus())) {

            throw new IllegalArgumentException(
                    "Booking is already cancelled"
            );
        }

        booking.setStatus("CANCELLED");

        return bookingRepository.save(booking);
    }

    // DELETE
    public void deleteBooking(Integer id) {

        Booking existingBooking =
                bookingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found with ID: "
                                                + id
                                )
                        );

        bookingRepository.delete(existingBooking);
    }

    // BASIC BOOKING VALIDATION
    private void validateBooking(Booking booking) {

        if (booking.getUserId() == null) {

            throw new IllegalArgumentException(
                    "User ID is required"
            );
        }

        if (booking.getPackageId() == null) {

            throw new IllegalArgumentException(
                    "Package ID is required"
            );
        }

        if (booking.getTravelStartDate() == null ||
                booking.getTravelEndDate() == null) {

            throw new IllegalArgumentException(
                    "Travel start date and end date are required"
            );
        }

        if (booking.getTravelEndDate()
                .isBefore(
                        booking.getTravelStartDate())) {

            throw new IllegalArgumentException(
                    "Travel end date cannot be before start date"
            );
        }

        if (booking.getNumberOfTravellers() == null ||
                booking.getNumberOfTravellers() <= 0) {

            throw new IllegalArgumentException(
                    "Number of travellers must be greater than zero"
            );
        }

        if (booking.getTotalAmount() == null ||
                booking.getTotalAmount()
                        .compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Total amount cannot be negative"
            );
        }
    }

    // RELATIONSHIP + AVAILABILITY VALIDATION
    private void validateRelationships(
            Booking booking) {

        // USER
        userRepository.findById(
                booking.getUserId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "User not found with ID: "
                                + booking.getUserId()
                )
        );

        // TOUR PACKAGE
        tourPackageRepository.findById(
                booking.getPackageId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Tour package not found with ID: "
                                + booking.getPackageId()
                )
        );

        // GUIDE
        if (booking.getGuideId() != null) {

            tourGuideRepository.findById(
                    booking.getGuideId()
            ).orElseThrow(() ->
                    new RuntimeException(
                            "Tour guide not found with ID: "
                                    + booking.getGuideId()
                    )
            );

            boolean guideAvailable =
                    tourGuideRepository
                            .findByAvailabilityStatusIgnoreCase(
                                    "AVAILABLE"
                            )
                            .stream()
                            .anyMatch(guide ->
                                    guide.getGuideId()
                                            .equals(
                                                    booking.getGuideId()
                                            )
                            );

            if (!guideAvailable) {

                throw new IllegalArgumentException(
                        "Selected tour guide is not available"
                );
            }
        }

        // VEHICLE
        if (booking.getVehicleId() != null) {

            Vehicle vehicle =
                    vehicleRepository.findById(
                            booking.getVehicleId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Vehicle not found with ID: "
                                            + booking.getVehicleId()
                            )
                    );

            boolean vehicleAvailable =
                    vehicleRepository
                            .findByStatusIgnoreCase(
                                    "AVAILABLE"
                            )
                            .stream()
                            .anyMatch(availableVehicle ->
                                    availableVehicle
                                            .getVehicleId()
                                            .equals(
                                                    booking.getVehicleId()
                                            )
                            );

            if (!vehicleAvailable) {

                throw new IllegalArgumentException(
                        "Selected vehicle is not available"
                );
            }

            // VEHICLE CAPACITY
            if (vehicle.getCapacity() <
                    booking.getNumberOfTravellers()) {

                throw new IllegalArgumentException(
                        "Vehicle capacity is not sufficient "
                                + "for the number of travellers"
                );
            }
        }

        // ROOM
        if (booking.getRoomId() != null) {

            Room room =
                    roomRepository.findById(
                            booking.getRoomId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Room not found with ID: "
                                            + booking.getRoomId()
                            )
                    );

            boolean roomAvailable =
                    roomRepository
                            .findByAvailabilityStatusIgnoreCase(
                                    "AVAILABLE"
                            )
                            .stream()
                            .anyMatch(availableRoom ->
                                    availableRoom
                                            .getRoomId()
                                            .equals(
                                                    booking.getRoomId()
                                            )
                            );

            if (!roomAvailable) {

                throw new IllegalArgumentException(
                        "Selected room is not available"
                );
            }

            // ROOM CAPACITY
            if (room.getCapacity() <
                    booking.getNumberOfTravellers()) {

                throw new IllegalArgumentException(
                        "Room capacity is not sufficient "
                                + "for the number of travellers"
                );
            }
        }
    }

    // RESOURCE CONFLICT VALIDATION
    private void validateResourceConflicts(
            Booking booking,
            Integer currentBookingId) {

        LocalDate startDate =
                booking.getTravelStartDate();

        LocalDate endDate =
                booking.getTravelEndDate();

        // GUIDE CONFLICT
        if (booking.getGuideId() != null) {

            boolean conflict =
                    bookingRepository
                            .findGuideConflictingBookings(
                                    booking.getGuideId(),
                                    startDate,
                                    endDate
                            )
                            .stream()
                            .anyMatch(existing ->
                                    currentBookingId == null
                                            ||
                                            !existing.getBookingId()
                                                    .equals(
                                                            currentBookingId
                                                    )
                            );

            if (conflict) {

                throw new IllegalArgumentException(
                        "Tour guide is already booked "
                                + "for the selected dates"
                );
            }
        }

        // VEHICLE CONFLICT
        if (booking.getVehicleId() != null) {

            boolean conflict =
                    bookingRepository
                            .findVehicleConflictingBookings(
                                    booking.getVehicleId(),
                                    startDate,
                                    endDate
                            )
                            .stream()
                            .anyMatch(existing ->
                                    currentBookingId == null
                                            ||
                                            !existing.getBookingId()
                                                    .equals(
                                                            currentBookingId
                                                    )
                            );

            if (conflict) {

                throw new IllegalArgumentException(
                        "Vehicle is already booked "
                                + "for the selected dates"
                );
            }
        }

        // ROOM CONFLICT
        if (booking.getRoomId() != null) {

            boolean conflict =
                    bookingRepository
                            .findRoomConflictingBookings(
                                    booking.getRoomId(),
                                    startDate,
                                    endDate
                            )
                            .stream()
                            .anyMatch(existing ->
                                    currentBookingId == null
                                            ||
                                            !existing.getBookingId()
                                                    .equals(
                                                            currentBookingId
                                                    )
                            );

            if (conflict) {

                throw new IllegalArgumentException(
                        "Room is already booked "
                                + "for the selected dates"
                );
            }
        }
    }
}