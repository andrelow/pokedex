package com.pokedex.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genderId;

    private double malePercent;
    private double femalePercent;

    public double getMalePercent() {
        return malePercent;
    }

    public void setMalePercent(double malePercent) {
        this.malePercent = malePercent;
    }

    public double getFemalePercent() {
        return femalePercent;
    }

    public void setFemalePercent(double femalePercent) {
        this.femalePercent = femalePercent;
    }
}
