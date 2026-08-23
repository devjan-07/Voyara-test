package com.tourguide.web_based_tour_guide.repository;

import com.tourguide.web_based_tour_guide.entity.GuideSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuideSpecialtyRepository
        extends JpaRepository<GuideSpecialty, Integer> {

    List<GuideSpecialty> findByGuideId(Integer guideId);

    List<GuideSpecialty> findBySpecialtyIgnoreCase(String specialty);

    boolean existsByGuideIdAndSpecialtyIgnoreCase(
            Integer guideId,
            String specialty
    );
}