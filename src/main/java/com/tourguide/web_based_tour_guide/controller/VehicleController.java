package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Vehicle;
import com.tourguide.web_based_tour_guide.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(
            @RequestBody Vehicle vehicle) {

        Vehicle createdVehicle =
                vehicleService.createVehicle(vehicle);

        return new ResponseEntity<>(
                createdVehicle,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {

        return ResponseEntity.ok(
                vehicleService.getAllVehicles()
        );
    }

    // READ ONE
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(
            @PathVariable Integer vehicleId) {

        return ResponseEntity.ok(
                vehicleService.getVehicleById(vehicleId)
        );
    }

    // UPDATE
    @PutMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable Integer vehicleId,
            @RequestBody Vehicle vehicle) {

        return ResponseEntity.ok(
                vehicleService.updateVehicle(
                        vehicleId,
                        vehicle
                )
        );
    }

    // DELETE
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(
            @PathVariable Integer vehicleId) {

        vehicleService.deleteVehicle(vehicleId);

        return ResponseEntity.noContent().build();
    }

    // AVAILABLE VEHICLES
    @GetMapping("/available")
    public ResponseEntity<List<Vehicle>> getAvailableVehicles() {

        return ResponseEntity.ok(
                vehicleService.getAvailableVehicles()
        );
    }

    // MAINTENANCE STATUS
    @GetMapping("/maintenance/{maintenanceStatus}")
    public ResponseEntity<List<Vehicle>>
    getVehiclesByMaintenanceStatus(
            @PathVariable String maintenanceStatus) {

        return ResponseEntity.ok(
                vehicleService.getVehiclesByMaintenanceStatus(
                        maintenanceStatus
                )
        );
    }
}