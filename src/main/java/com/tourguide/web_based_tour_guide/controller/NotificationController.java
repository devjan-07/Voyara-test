package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Notification;
import com.tourguide.web_based_tour_guide.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @RequestBody Notification notification) {

        return new ResponseEntity<>(
                notificationService.createNotification(notification),
                HttpStatus.CREATED
        );
    }

    // GET USER NOTIFICATIONS
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(
            @PathVariable Integer userId) {

        return ResponseEntity.ok(
                notificationService.getUserNotifications(userId)
        );
    }

    // MARK AS READ
    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                notificationService.markAsRead(id)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable Integer id) {

        notificationService.deleteNotification(id);

        return ResponseEntity.noContent().build();
    }
}