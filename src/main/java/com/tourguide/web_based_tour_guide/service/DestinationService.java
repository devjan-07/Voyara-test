package com.tourguide.web_based_tour_guide.service;

import com.tourguide.web_based_tour_guide.entity.Destination;
import com.tourguide.web_based_tour_guide.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination createDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Destination getDestinationById(Integer id) {
        return destinationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Destination not found with ID: " + id
                        ));
    }

    public Destination updateDestination(Integer id, Destination destination) {

        Destination existing = getDestinationById(id);

        existing.setName(destination.getName());
        existing.setDescription(destination.getDescription());
        existing.setLocation(destination.getLocation());
        existing.setCategory(destination.getCategory());
        existing.setStatus(destination.getStatus());

        return destinationRepository.save(existing);
    }

    public void deleteDestination(Integer id) {

        Destination existing = getDestinationById(id);

        destinationRepository.delete(existing);
    }

    public List<Destination> searchDestinations(String keyword) {
        return destinationRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Destination> getDestinationsByCategory(String category) {
        return destinationRepository.findByCategoryIgnoreCase(category);
    }

}