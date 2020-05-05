package com.pokedex.domain;

import javax.persistence.*;

@Entity
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cameraId;

    private double cylinderRadius;
    private double diskRadius;
    private double shoulderModeScale;

    public double getCylinderRadius() {
        return cylinderRadius;
    }

    public void setCylinderRadius(double cylinderRadius) {
        this.cylinderRadius = cylinderRadius;
    }

    public double getDiskRadius() {
        return diskRadius;
    }

    public void setDiskRadius(double diskRadius) {
        this.diskRadius = diskRadius;
    }

    public double getShoulderModeScale() {
        return shoulderModeScale;
    }

    public void setShoulderModeScale(double shoulderModeScale) {
        this.shoulderModeScale = shoulderModeScale;
    }
}
