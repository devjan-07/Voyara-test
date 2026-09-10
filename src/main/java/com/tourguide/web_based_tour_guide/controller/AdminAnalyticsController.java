package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.service.AdminAnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/analytics")
public class AdminAnalyticsController {

    private final AdminAnalyticsService analyticsService;

    public AdminAnalyticsController(
            AdminAnalyticsService analyticsService) {

        this.analyticsService = analyticsService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Long>> getAnalytics() {

        return ResponseEntity.ok(
                analyticsService.getAnalytics()
        );
    }
}