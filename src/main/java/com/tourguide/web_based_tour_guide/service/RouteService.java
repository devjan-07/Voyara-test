package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Route;
import com.tourguide.web_based_tour_guide.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(Integer id) {
        return routeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Route not found with ID: " + id));
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(Integer id, Route route) {
        Route existingRoute = getRouteById(id);

        existingRoute.setDestination(route.getDestination());
        existingRoute.setRouteName(route.getRouteName());
        existingRoute.setStartLocation(route.getStartLocation());
        existingRoute.setEndLocation(route.getEndLocation());
        existingRoute.setDistanceKm(route.getDistanceKm());
        existingRoute.setEstimatedDuration(route.getEstimatedDuration());
        existingRoute.setDescription(route.getDescription());
        existingRoute.setStatus(route.getStatus());

        return routeRepository.save(existingRoute);
    }

    public void deleteRoute(Integer id) {
        routeRepository.deleteById(id);
    }

    public List<Route> getRoutesByDestination(Integer destinationId) {
        return routeRepository.findByDestinationDestinationId(destinationId);
    }

    public List<Route> getActiveRoutesByDestination(Integer destinationId) {
        return routeRepository.findByDestinationDestinationIdAndStatus(
                destinationId,
                "ACTIVE"
        );
    }

    public List<Route> searchRoutes(String routeName) {
        return routeRepository.findByRouteNameContainingIgnoreCase(routeName);
    }

    public List<Route> getRoutesByStatus(String status) {
        return routeRepository.findByStatus(status);
    }
}