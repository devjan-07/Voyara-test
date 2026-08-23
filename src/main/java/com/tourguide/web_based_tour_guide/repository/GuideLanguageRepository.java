package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.GuideLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuideLanguageRepository
        extends JpaRepository<GuideLanguage, Integer> {

    List<GuideLanguage> findByGuideId(Integer guideId);

    List<GuideLanguage> findByLanguageIgnoreCase(String language);

    boolean existsByGuideIdAndLanguageIgnoreCase(
            Integer guideId,
            String language
    );
}