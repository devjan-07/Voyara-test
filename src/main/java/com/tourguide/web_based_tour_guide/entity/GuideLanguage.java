package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "GuideLanguages",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UQ_GuideLanguages",
                        columnNames = {"GuideID", "Language"}
                )
        }
)
public class GuideLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GuideLanguageID")
    private Integer guideLanguageId;

    @Column(name = "GuideID", nullable = false)
    private Integer guideId;

    @Column(name = "Language", nullable = false, length = 50)
    private String language;

    public GuideLanguage() {
    }

    public Integer getGuideLanguageId() {
        return guideLanguageId;
    }

    public void setGuideLanguageId(Integer guideLanguageId) {
        this.guideLanguageId = guideLanguageId;
    }

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}