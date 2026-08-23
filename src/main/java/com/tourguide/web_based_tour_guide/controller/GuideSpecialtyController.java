package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.GuideSpecialty;
import com.tourguide.web_based_tour_guide.service.GuideSpecialtyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guide-specialties")
public class GuideSpecialtyController {

    private final GuideSpecialtyService guideSpecialtyService;

    public GuideSpecialtyController(
            GuideSpecialtyService guideSpecialtyService) {

        this.guideSpecialtyService = guideSpecialtyService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<GuideSpecialty> createGuideSpecialty(
            @RequestBody GuideSpecialty guideSpecialty) {

        GuideSpecialty createdSpecialty =
                guideSpecialtyService.createGuideSpecialty(
                        guideSpecialty
                );

        return new ResponseEntity<>(
                createdSpecialty,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<GuideSpecialty>>
    getAllGuideSpecialties() {

        return ResponseEntity.ok(
                guideSpecialtyService.getAllGuideSpecialties()
        );
    }

    // READ ONE
    @GetMapping("/{guideSpecialtyId}")
    public ResponseEntity<GuideSpecialty>
    getGuideSpecialtyById(
            @PathVariable Integer guideSpecialtyId) {

        return ResponseEntity.ok(
                guideSpecialtyService.getGuideSpecialtyById(
                        guideSpecialtyId
                )
        );
    }

    // GET SPECIALTIES OF A SPECIFIC GUIDE
    @GetMapping("/guide/{guideId}")
    public ResponseEntity<List<GuideSpecialty>>
    getSpecialtiesByGuideId(
            @PathVariable Integer guideId) {

        return ResponseEntity.ok(
                guideSpecialtyService.getSpecialtiesByGuideId(
                        guideId
                )
        );
    }

    // UPDATE
    @PutMapping("/{guideSpecialtyId}")
    public ResponseEntity<GuideSpecialty>
    updateGuideSpecialty(
            @PathVariable Integer guideSpecialtyId,
            @RequestBody GuideSpecialty guideSpecialty) {

        return ResponseEntity.ok(
                guideSpecialtyService.updateGuideSpecialty(
                        guideSpecialtyId,
                        guideSpecialty
                )
        );
    }

    // DELETE
    @DeleteMapping("/{guideSpecialtyId}")
    public ResponseEntity<Void> deleteGuideSpecialty(
            @PathVariable Integer guideSpecialtyId) {

        guideSpecialtyService.deleteGuideSpecialty(
                guideSpecialtyId
        );

        return ResponseEntity.noContent().build();
    }
}