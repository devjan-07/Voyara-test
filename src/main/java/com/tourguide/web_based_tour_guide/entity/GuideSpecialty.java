package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "GuideSpecialties",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UQ_GuideSpecialties",
                        columnNames = {"GuideID", "Specialty"}
                )
        }
)
public class GuideSpecialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GuideSpecialtyID")
    private Integer guideSpecialtyId;

    @Column(name = "GuideID", nullable = false)
    private Integer guideId;

    @Column(name = "Specialty", nullable = false, length = 100)
    private String specialty;

    public GuideSpecialty() {
    }

    public Integer getGuideSpecialtyId() {
        return guideSpecialtyId;
    }

    public void setGuideSpecialtyId(Integer guideSpecialtyId) {
        this.guideSpecialtyId = guideSpecialtyId;
    }

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}