package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TourGuides")
public class TourGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GuideID")
    private Integer guideId;

    @Column(name = "UserID", nullable = false, unique = true)
    private Integer userId;

    @Column(name = "ExperienceYears", nullable = false)
    private Integer experienceYears = 0;

    @Column(name = "Bio", columnDefinition = "VARCHAR(MAX)")
    private String bio;

    @Column(name = "AvailabilityStatus", nullable = false, length = 20)
    private String availabilityStatus = "AVAILABLE";

    @Column(name = "Rating", nullable = false, precision = 3, scale = 2)
    private BigDecimal rating = BigDecimal.ZERO;

    @Column(name = "Status", nullable = false, length = 20)
    private String status = "ACTIVE";

    public TourGuide() {
    }

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}