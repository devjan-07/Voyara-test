package com.tourguide.web_based_tour_guide.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PackageDestinations")
public class PackageDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PackageDestinationID")
    private Integer packageDestinationId;

    @Column(name = "PackageID", nullable = false)
    private Integer packageId;

    @Column(name = "DestinationID", nullable = false)
    private Integer destinationId;

    @Column(name = "SequenceNo", nullable = false)
    private Integer sequenceNo;

    @Column(name = "DaysAllocated", nullable = false)
    private Integer daysAllocated = 1;

    public PackageDestination() {
    }

    public Integer getPackageDestinationId() {
        return packageDestinationId;
    }

    public void setPackageDestinationId(Integer packageDestinationId) {
        this.packageDestinationId = packageDestinationId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public Integer getDaysAllocated() {
        return daysAllocated;
    }

    public void setDaysAllocated(Integer daysAllocated) {
        this.daysAllocated = daysAllocated;
    }
}