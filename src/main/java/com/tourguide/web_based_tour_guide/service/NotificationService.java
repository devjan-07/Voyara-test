package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Notification;
import com.tourguide.web_based_tour_guide.repository.NotificationRepository;
import com.tourguide.web_based_tour_guide.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(
            NotificationRepository notificationRepository,
            UserRepository userRepository) {

        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public Notification createNotification(
            Notification notification) {

        if (notification.getUserId() == null) {
            throw new IllegalArgumentException(
                    "User ID is required"
            );
        }

        if (notification.getMessage() == null ||
                notification.getMessage().isBlank()) {

            throw new IllegalArgumentException(
                    "Notification message is required"
            );
        }

        userRepository.findById(notification.getUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID: "
                                        + notification.getUserId()
                        ));

        notification.setIsRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    // GET USER NOTIFICATIONS
    public List<Notification> getUserNotifications(
            Integer userId) {

        userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID: " + userId
                        ));

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId);
    }

    // MARK AS READ
    public Notification markAsRead(Integer id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found with ID: "
                                                + id
                                ));

        notification.setIsRead(true);

        return notificationRepository.save(notification);
    }

    // DELETE
    public void deleteNotification(Integer id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found with ID: "
                                                + id
                                ));

        notificationRepository.delete(notification);
    }
}