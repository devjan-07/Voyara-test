package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Vehicle;
import com.tourguide.web_based_tour_guide.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // CREATE
    public Vehicle createVehicle(Vehicle vehicle) {

        validateVehicle(vehicle);

        if (vehicleRepository.existsByRegistrationNoIgnoreCase(
                vehicle.getRegistrationNo())) {

            throw new IllegalArgumentException(
                    "Vehicle with registration number already exists: "
                            + vehicle.getRegistrationNo()
            );
        }

        if (vehicle.getStatus() == null ||
                vehicle.getStatus().isBlank()) {

            vehicle.setStatus("AVAILABLE");
        }

        if (vehicle.getMaintenanceStatus() == null ||
                vehicle.getMaintenanceStatus().isBlank()) {

            vehicle.setMaintenanceStatus("GOOD");
        }

        return vehicleRepository.save(vehicle);
    }

    // READ ALL
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // READ ONE
    public Vehicle getVehicleById(Integer vehicleId) {

        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Vehicle not found with ID: " + vehicleId
                        ));
    }

    // UPDATE
    public Vehicle updateVehicle(
            Integer vehicleId,
            Vehicle vehicle) {

        Vehicle existingVehicle =
                getVehicleById(vehicleId);

        validateVehicle(vehicle);

        if (!existingVehicle.getRegistrationNo()
                .equalsIgnoreCase(vehicle.getRegistrationNo())
                && vehicleRepository.existsByRegistrationNoIgnoreCase(
                vehicle.getRegistrationNo())) {

            throw new IllegalArgumentException(
                    "Vehicle with registration number already exists: "
                            + vehicle.getRegistrationNo()
            );
        }

        existingVehicle.setVehicleType(
                vehicle.getVehicleType()
        );

        existingVehicle.setRegistrationNo(
                vehicle.getRegistrationNo()
        );

        existingVehicle.setCapacity(
                vehicle.getCapacity()
        );

        existingVehicle.setStatus(
                vehicle.getStatus()
        );

        existingVehicle.setMaintenanceStatus(
                vehicle.getMaintenanceStatus()
        );

        existingVehicle.setDriverName(
                vehicle.getDriverName()
        );

        return vehicleRepository.save(existingVehicle);
    }

    // DELETE
    public void deleteVehicle(Integer vehicleId) {

        Vehicle existingVehicle =
                getVehicleById(vehicleId);

        vehicleRepository.delete(existingVehicle);
    }

    // GET AVAILABLE VEHICLES
    public List<Vehicle> getAvailableVehicles() {

        return vehicleRepository
                .findByStatusIgnoreCase("AVAILABLE")
                .stream()
                .filter(vehicle ->
                        !"UNDER_MAINTENANCE".equalsIgnoreCase(
                                vehicle.getMaintenanceStatus()
                        ))
                .toList();
    }

    // GET BY MAINTENANCE STATUS
    public List<Vehicle> getVehiclesByMaintenanceStatus(
            String maintenanceStatus) {

        return vehicleRepository
                .findByMaintenanceStatusIgnoreCase(
                        maintenanceStatus
                );
    }

    // VALIDATION
    private void validateVehicle(Vehicle vehicle) {

        if (vehicle.getVehicleType() == null ||
                vehicle.getVehicleType().isBlank()) {

            throw new IllegalArgumentException(
                    "Vehicle type is required"
            );
        }

        if (vehicle.getRegistrationNo() == null ||
                vehicle.getRegistrationNo().isBlank()) {

            throw new IllegalArgumentException(
                    "Registration number is required"
            );
        }

        if (vehicle.getCapacity() == null ||
                vehicle.getCapacity() <= 0) {

            throw new IllegalArgumentException(
                    "Vehicle capacity must be greater than zero"
            );
        }

        if (vehicle.getStatus() != null &&
                !isValidStatus(vehicle.getStatus())) {

            throw new IllegalArgumentException(
                    "Invalid vehicle status"
            );
        }

        if (vehicle.getMaintenanceStatus() != null &&
                !isValidMaintenanceStatus(
                        vehicle.getMaintenanceStatus())) {

            throw new IllegalArgumentException(
                    "Invalid maintenance status"
            );
        }
    }

    private boolean isValidStatus(String status) {

        return status.equalsIgnoreCase("AVAILABLE") ||
                status.equalsIgnoreCase("UNAVAILABLE");
    }

    private boolean isValidMaintenanceStatus(
            String status) {

        return status.equalsIgnoreCase("GOOD") ||
                status.equalsIgnoreCase("UNDER_MAINTENANCE");
    }
}