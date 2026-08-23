package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Routes", schema = "dbo")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RouteID")
    private Integer routeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DestinationID", nullable = false)
    private Destination destination;

    @Column(name = "RouteName", nullable = false, length = 150)
    private String routeName;

    @Column(name = "StartLocation", nullable = false, length = 150)
    private String startLocation;

    @Column(name = "EndLocation", nullable = false, length = 150)
    private String endLocation;

    @Column(name = "DistanceKm", precision = 8, scale = 2)
    private BigDecimal distanceKm;

    @Column(name = "EstimatedDuration")
    private Integer estimatedDuration;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    public Route() {
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public BigDecimal getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(BigDecimal distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}