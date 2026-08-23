package com.tourguide.web_based_tour_guide.controller;

import com.tourguide.web_based_tour_guide.entity.GuideLanguage;
import com.tourguide.web_based_tour_guide.service.GuideLanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guide-languages")
public class GuideLanguageController {

    private final GuideLanguageService guideLanguageService;

    public GuideLanguageController(
            GuideLanguageService guideLanguageService) {

        this.guideLanguageService = guideLanguageService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<GuideLanguage> createGuideLanguage(
            @RequestBody GuideLanguage guideLanguage) {

        GuideLanguage createdLanguage =
                guideLanguageService.createGuideLanguage(
                        guideLanguage
                );

        return new ResponseEntity<>(
                createdLanguage,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<GuideLanguage>>
    getAllGuideLanguages() {

        return ResponseEntity.ok(
                guideLanguageService.getAllGuideLanguages()
        );
    }

    // READ ONE
    @GetMapping("/{guideLanguageId}")
    public ResponseEntity<GuideLanguage> getGuideLanguageById(
            @PathVariable Integer guideLanguageId) {

        return ResponseEntity.ok(
                guideLanguageService.getGuideLanguageById(
                        guideLanguageId
                )
        );
    }

    // GET LANGUAGES OF A SPECIFIC GUIDE
    @GetMapping("/guide/{guideId}")
    public ResponseEntity<List<GuideLanguage>>
    getLanguagesByGuideId(
            @PathVariable Integer guideId) {

        return ResponseEntity.ok(
                guideLanguageService.getLanguagesByGuideId(
                        guideId
                )
        );
    }

    // UPDATE
    @PutMapping("/{guideLanguageId}")
    public ResponseEntity<GuideLanguage> updateGuideLanguage(
            @PathVariable Integer guideLanguageId,
            @RequestBody GuideLanguage guideLanguage) {

        return ResponseEntity.ok(
                guideLanguageService.updateGuideLanguage(
                        guideLanguageId,
                        guideLanguage
                )
        );
    }

    // DELETE
    @DeleteMapping("/{guideLanguageId}")
    public ResponseEntity<Void> deleteGuideLanguage(
            @PathVariable Integer guideLanguageId) {

        guideLanguageService.deleteGuideLanguage(
                guideLanguageId
        );

        return ResponseEntity.noContent().build();
    }
}