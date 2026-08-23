package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Route;
import com.tourguide.web_based_tour_guide.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable Integer id) {
        return routeService.getRouteById(id);
    }

    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeService.createRoute(route);
    }

    @PutMapping("/{id}")
    public Route updateRoute(
            @PathVariable Integer id,
            @RequestBody Route route) {

        return routeService.updateRoute(id, route);
    }

    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable Integer id) {
        routeService.deleteRoute(id);
    }

    @GetMapping("/destination/{destinationId}")
    public List<Route> getRoutesByDestination(
            @PathVariable Integer destinationId) {

        return routeService.getRoutesByDestination(destinationId);
    }

    @GetMapping("/search")
    public List<Route> searchRoutes(
            @RequestParam String name) {

        return routeService.searchRoutes(name);
    }
}