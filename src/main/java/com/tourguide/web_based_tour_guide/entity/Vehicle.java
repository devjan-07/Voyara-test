package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleID")
    private Integer vehicleId;

    @Column(name = "VehicleType", nullable = false, length = 50)
    private String vehicleType;

    @Column(name = "RegistrationNo", nullable = false, unique = true, length = 30)
    private String registrationNo;

    @Column(name = "Capacity", nullable = false)
    private Integer capacity;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @Column(name = "MaintenanceStatus", nullable = false, length = 20)
    private String maintenanceStatus;

    @Column(name = "DriverName", length = 100)
    private String driverName;

    public Vehicle() {
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
