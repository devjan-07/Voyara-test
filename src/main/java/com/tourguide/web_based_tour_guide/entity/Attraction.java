package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Attractions", schema = "dbo")
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttractionID")
    private Integer attractionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DestinationID", nullable = false)
    private Destination destination;

    @Column(name = "Name", nullable = false, length = 150)
    private String name;

    @Column(name = "Description")
    private String description;


    @Column(name = "OpeningTime")
    private java.time.LocalTime openingTime;

    @Column(name = "ClosingTime")
    private java.time.LocalTime closingTime;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    public Attraction() {
    }

    public Integer getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Integer attractionId) {
        this.attractionId = attractionId;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.time.LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(java.time.LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public java.time.LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(java.time.LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}