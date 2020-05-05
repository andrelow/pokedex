package com.pokedex.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CostToEvolve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long costToEvolveId;

    private int candyCost;

    public int getCandyCost() {
        return candyCost;
    }

    public void setCandyCost(int candyCost) {
        this.candyCost = candyCost;
    }
}
