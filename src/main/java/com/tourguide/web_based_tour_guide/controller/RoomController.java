package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Room;
import com.tourguide.web_based_tour_guide.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(
            @RequestBody Room room) {

        return ResponseEntity.ok(
                roomService.createRoom(room)
        );
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {

        return ResponseEntity.ok(
                roomService.getAllRooms()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(
            @PathVariable Integer id) {

        return roomService.getRoomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/accommodation/{accommodationId}")
    public ResponseEntity<List<Room>> getRoomsByAccommodation(
            @PathVariable Integer accommodationId) {

        return ResponseEntity.ok(
                roomService.getRoomsByAccommodation(accommodationId)
        );
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms() {

        return ResponseEntity.ok(
                roomService.getAvailableRooms()
        );
    }

    @GetMapping("/available/accommodation/{accommodationId}")
    public ResponseEntity<List<Room>> getAvailableRoomsByAccommodation(
            @PathVariable Integer accommodationId) {

        return ResponseEntity.ok(
                roomService.getAvailableRoomsByAccommodation(
                        accommodationId
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<Room>> searchRooms(
            @RequestParam String roomType) {

        return ResponseEntity.ok(
                roomService.searchRoomsByType(roomType)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable Integer id,
            @RequestBody Room room) {

        return ResponseEntity.ok(
                roomService.updateRoom(id, room)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(
            @PathVariable Integer id) {

        roomService.deleteRoom(id);

        return ResponseEntity.noContent().build();
    }
}