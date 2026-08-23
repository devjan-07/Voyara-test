package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomID")
    private Integer roomId;

    @Column(name = "AccommodationID", nullable = false)
    private Integer accommodationId;

    @Column(name = "RoomType", nullable = false, length = 50)
    private String roomType;

    @Column(name = "Capacity", nullable = false)
    private Integer capacity;

    @Column(name = "PricePerNight", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerNight;

    @Column(name = "AvailabilityStatus", nullable = false, length = 20)
    private String availabilityStatus = "AVAILABLE";

    @Column(name = "Status", nullable = false, length = 20)
    private String status = "ACTIVE";

    public Room() {
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Integer accommodationId) {
        this.accommodationId = accommodationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}