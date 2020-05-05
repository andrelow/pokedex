package com.pokedex.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Pokemon {
    @Id
    private Long dex;
    private String name;

    @ElementCollection
    private List<Double> animationTime;

    private double height;
    private double modelHeight;
    private int kmBuddyDistance;
    private double weight;
    private double modelScale;
    private int maxCP;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buddySizeId")
    private BuddySize buddySize;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pokemonCinematicMoves",
            joinColumns = @JoinColumn(name = "dexId"),
            inverseJoinColumns = @JoinColumn(name = "cinematicMovesId")
    )
    private List<CinematicMoves> cinematicMoves;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pokemonQuickMoves",
            joinColumns = @JoinColumn(name = "dexId"),
            inverseJoinColumns = @JoinColumn(name = "quickMovesId")
    )
    private List<QuickMoves> quickMoves;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "familyId")
    private Family family;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "statsId")
    private Stats stats;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pokemonTypes",
            joinColumns = @JoinColumn(name = "dexId"),
            inverseJoinColumns = @JoinColumn(name = "typesId")
    )
    private Set<Types> types;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "encounterId")
    private Encounter encounter;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cameraId")
    private Camera camera;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evolutionId")
    private Evolution evolution;

    private String id;

    public Long getDex() {
        return dex;
    }

    public void setDex(Long dex) {
        this.dex = dex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getAnimationTime() {
        return animationTime;
    }

    public void setAnimationTime(List<Double> animationTime) {
        this.animationTime = animationTime;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getModelHeight() {
        return modelHeight;
    }

    public void setModelHeight(double modelHeight) {
        this.modelHeight = modelHeight;
    }

    public int getKmBuddyDistance() {
        return kmBuddyDistance;
    }

    public void setKmBuddyDistance(int kmBuddyDistance) {
        this.kmBuddyDistance = kmBuddyDistance;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getModelScale() {
        return modelScale;
    }

    public void setModelScale(double modelScale) {
        this.modelScale = modelScale;
    }

    public int getMaxCP() {
        return maxCP;
    }

    public void setMaxCP(int maxCP) {
        this.maxCP = maxCP;
    }

    public BuddySize getBuddySize() {
        return buddySize;
    }

    public void setBuddySize(BuddySize buddySize) {
        this.buddySize = buddySize;
    }

    public List<CinematicMoves> getCinematicMoves() {
        return cinematicMoves;
    }

    public void setCinematicMoves(List<CinematicMoves> cinematicMoves) {
        this.cinematicMoves = cinematicMoves;
    }

    public List<QuickMoves> getQuickMoves() {
        return quickMoves;
    }

    public void setQuickMoves(List<QuickMoves> quickMoves) {
        this.quickMoves = quickMoves;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Set<Types> getTypes() {
        return types;
    }

    public void setTypes(Set<Types> types) {
        this.types = types;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Evolution getEvolution() {
        return evolution;
    }

    public void setEvolution(Evolution evolution) {
        this.evolution = evolution;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
