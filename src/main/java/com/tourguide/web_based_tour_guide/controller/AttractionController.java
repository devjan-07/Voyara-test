package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Attraction;
import com.tourguide.web_based_tour_guide.service.AttractionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping
    public List<Attraction> getAllAttractions() {
        return attractionService.getAllAttractions();
    }

    @GetMapping("/{id}")
    public Attraction getAttractionById(@PathVariable Integer id) {
        return attractionService.getAttractionById(id);
    }

    @PostMapping
    public Attraction createAttraction(
            @RequestBody Attraction attraction) {
        return attractionService.createAttraction(attraction);
    }

    @PutMapping("/{id}")
    public Attraction updateAttraction(
            @PathVariable Integer id,
            @RequestBody Attraction attraction) {
        return attractionService.updateAttraction(id, attraction);
    }

    @DeleteMapping("/{id}")
    public void deleteAttraction(@PathVariable Integer id) {
        attractionService.deleteAttraction(id);
    }

    @GetMapping("/destination/{destinationId}")
    public List<Attraction> getByDestination(
            @PathVariable Integer destinationId) {
        return attractionService.getAttractionsByDestination(destinationId);
    }

    @GetMapping("/search")
    public List<Attraction> search(
            @RequestParam String name) {
        return attractionService.searchAttractions(name);
    }
}