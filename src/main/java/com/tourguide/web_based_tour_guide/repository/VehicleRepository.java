package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findByStatusIgnoreCase(String status);

    List<Vehicle> findByMaintenanceStatusIgnoreCase(String maintenanceStatus);

    boolean existsByRegistrationNoIgnoreCase(String registrationNo);
}