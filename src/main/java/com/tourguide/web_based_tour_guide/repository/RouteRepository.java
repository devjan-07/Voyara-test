package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    List<Route> findByDestinationDestinationId(Integer destinationId);

    List<Route> findByDestinationDestinationIdAndStatus(
            Integer destinationId,
            String status
    );

    List<Route> findByRouteNameContainingIgnoreCase(String routeName);

    List<Route> findByStatus(String status);
}