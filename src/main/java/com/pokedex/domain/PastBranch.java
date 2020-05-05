package com.pokedex.domain;

import javax.persistence.*;

@Entity
public class PastBranch {
    @Id
    private String id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pastBranchesNextId")
    private PastBranch pastBranch;

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

    public PastBranch getPastBranch() {
        return pastBranch;
    }

    public void setPastBranch(PastBranch pastBranch) {
        this.pastBranch = pastBranch;
    }

    public CostToEvolve getCostToEvolve() {
        return costToEvolve;
    }

    public void setCostToEvolve(CostToEvolve costToEvolve) {
        this.costToEvolve = costToEvolve;
    }
}
