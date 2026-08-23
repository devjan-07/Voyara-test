package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Accommodations")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccommodationID")
    private Integer accommodationId;

    @Column(name = "DestinationID", nullable = false)
    private Integer destinationId;

    @Column(name = "Name", nullable = false, length = 150)
    private String name;

    @Column(name = "Address", nullable = false, length = 250)
    private String address;

    @Column(name = "ContactNumber", length = 20)
    private String contactNumber;

    @Column(name = "Description", columnDefinition = "VARCHAR(MAX)")
    private String description;

    @Column(name = "Facilities", columnDefinition = "VARCHAR(MAX)")
    private String facilities;

    @Column(name = "Status", nullable = false, length = 20)
    private String status = "ACTIVE";

    public Accommodation() {
    }

    public Integer getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Integer accommodationId) {
        this.accommodationId = accommodationId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}