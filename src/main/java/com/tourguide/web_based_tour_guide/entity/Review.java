package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(
        name = "Reviews",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"UserID", "PackageID"}
                )
        }
)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private Integer reviewId;

    @Column(name = "UserID", nullable = false)
    private Integer userId;

    @Column(name = "PackageID", nullable = false)
    private Integer packageId;

    @Column(name = "Rating", nullable = false)
    private BigDecimal rating;

    @Column(name = "Comment", nullable = false, columnDefinition = "VARCHAR(MAX)")
    private String comment;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    public Review() {
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}