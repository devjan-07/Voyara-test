package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Booking;
import com.tourguide.web_based_tour_guide.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody Booking booking) {

        return ResponseEntity.ok(
                bookingService.createBooking(booking)
        );
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {

        return ResponseEntity.ok(
                bookingService.getAllBookings()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(
            @PathVariable Integer id) {

        return bookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(
            @PathVariable Integer userId) {

        return ResponseEntity.ok(
                bookingService.getBookingsByUser(userId)
        );
    }

    @GetMapping("/package/{packageId}")
    public ResponseEntity<List<Booking>> getBookingsByPackage(
            @PathVariable Integer packageId) {

        return ResponseEntity.ok(
                bookingService.getBookingsByPackage(packageId)
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                bookingService.getBookingsByStatus(status)
        );
    }

    @GetMapping("/guide/{guideId}")
    public ResponseEntity<List<Booking>> getBookingsByGuide(
            @PathVariable Integer guideId) {

        return ResponseEntity.ok(
                bookingService.getBookingsByGuide(guideId)
        );
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Booking>> getBookingsByVehicle(
            @PathVariable Integer vehicleId) {

        return ResponseEntity.ok(
                bookingService.getBookingsByVehicle(vehicleId)
        );
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Booking>> getBookingsByRoom(
            @PathVariable Integer roomId) {

        return ResponseEntity.ok(
                bookingService.getBookingsByRoom(roomId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Integer id,
            @RequestBody Booking booking) {

        return ResponseEntity.ok(
                bookingService.updateBooking(id, booking)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(
            @PathVariable Integer id) {

        bookingService.deleteBooking(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                bookingService.cancelBooking(id)
        );
    }
}