package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Attraction;
import com.tourguide.web_based_tour_guide.repository.AttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;

    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public List<Attraction> getAllAttractions() {
        return attractionRepository.findAll();
    }

    public Attraction getAttractionById(Integer id) {
        return attractionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attraction not found with ID: " + id));
    }

    public Attraction createAttraction(Attraction attraction) {
        return attractionRepository.save(attraction);
    }

    public Attraction updateAttraction(Integer id, Attraction attraction) {
        Attraction existing = getAttractionById(id);

        existing.setDestination(attraction.getDestination());
        existing.setName(attraction.getName());
        existing.setDescription(attraction.getDescription());
        existing.setOpeningTime(attraction.getOpeningTime());
        existing.setClosingTime(attraction.getClosingTime());
        existing.setStatus(attraction.getStatus());

        return attractionRepository.save(existing);
    }

    public void deleteAttraction(Integer id) {
        attractionRepository.deleteById(id);
    }

    public List<Attraction> getAttractionsByDestination(Integer destinationId) {
        return attractionRepository.findByDestinationDestinationId(destinationId);
    }

    public List<Attraction> getActiveAttractionsByDestination(Integer destinationId) {
        return attractionRepository.findByDestinationDestinationIdAndStatus(
                destinationId,
                "ACTIVE"
        );
    }

    public List<Attraction> searchAttractions(String name) {
        return attractionRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Attraction> getAttractionsByStatus(String status) {
        return attractionRepository.findByStatus(status);
    }
}