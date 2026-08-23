package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.Destination;
import com.tourguide.web_based_tour_guide.service.DestinationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    // READ - Display all destinations
    @GetMapping
    public String listDestinations(Model model) {
        model.addAttribute(
                "destinations",
                destinationService.getAllDestinations()
        );

        return "destinations/list";
    }

    // CREATE - Show form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("destination", new Destination());

        return "destinations/form";
    }

    // CREATE - Save destination
    @PostMapping
    public String createDestination(
            @ModelAttribute("destination") Destination destination) {

        destinationService.createDestination(destination);

        return "redirect:/destinations";
    }

    // READ - View single destination
    @GetMapping("/{id}")
    public String viewDestination(
            @PathVariable Integer id,
            Model model) {

        model.addAttribute(
                "destination",
                destinationService.getDestinationById(id)
        );

        return "destinations/view";
    }

    // UPDATE - Show edit form
    @GetMapping("/{id}/edit")
    public String showEditForm(
            @PathVariable Integer id,
            Model model) {

        model.addAttribute(
                "destination",
                destinationService.getDestinationById(id)
        );

        return "destinations/form";
    }

    // UPDATE - Save changes
    @PostMapping("/{id}")
    public String updateDestination(
            @PathVariable Integer id,
            @ModelAttribute("destination") Destination destination) {

        destinationService.updateDestination(id, destination);

        return "redirect:/destinations";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String deleteDestination(@PathVariable Integer id) {

        destinationService.deleteDestination(id);

        return "redirect:/destinations";
    }

    // CREATIVE - Search
    @GetMapping("/search")
    public String searchDestinations(
            @RequestParam(required = false) String keyword,
            Model model) {

        if (keyword == null || keyword.trim().isEmpty()) {
            model.addAttribute(
                    "destinations",
                    destinationService.getAllDestinations()
            );
        } else {
            model.addAttribute(
                    "destinations",
                    destinationService.searchDestinations(keyword)
            );
        }

        model.addAttribute("keyword", keyword);

        return "destinations/list";
    }

    // CREATIVE - Category filter
    @GetMapping("/category")
    public String filterByCategory(
            @RequestParam String category,
            Model model) {

        model.addAttribute(
                "destinations",
                destinationService.getDestinationsByCategory(category)
        );

        model.addAttribute("selectedCategory", category);

        return "destinations/list";
    }
}