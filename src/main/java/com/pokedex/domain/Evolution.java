package com.pokedex.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Evolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evolutionid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pastBranchId")
    private PastBranch pastBranch;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costToEvolveId")
    private CostToEvolve costToEvolve;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "evolutionId")
    private Set<FutureBranches> futureBranches;

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

    public Set<FutureBranches> getFutureBranches() {
        return futureBranches;
    }

    public void setFutureBranches(Set<FutureBranches> futureBranches) {
        this.futureBranches = futureBranches;
    }
}
