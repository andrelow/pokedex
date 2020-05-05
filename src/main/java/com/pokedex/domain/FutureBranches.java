package com.pokedex.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class FutureBranches {
    @Id
    private String id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "futureBranchesNextId")
    private Set<FutureBranches> futureBranches;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costToEvolveId")
    private CostToEvolve costToEvolve;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<FutureBranches> getFutureBranches() {
        return futureBranches;
    }

    public void setFutureBranches(Set<FutureBranches> futureBranches) {
        this.futureBranches = futureBranches;
    }

    public CostToEvolve getCostToEvolve() {
        return costToEvolve;
    }

    public void setCostToEvolve(CostToEvolve costToEvolve) {
        this.costToEvolve = costToEvolve;
    }
}
