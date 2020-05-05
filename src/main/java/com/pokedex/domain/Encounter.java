package com.pokedex.domain;

import javax.persistence.*;

@Entity
public class Encounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long encounterId;

    private double attackProbability;
    private int attackTimer;
    private double baseFleeRate;
    private double baseCaptureRate;
    private double cameraDistance;
    private double collisionRadius;
    private double dodgeDistance;
    private double dodgeProbability;
    private double jumpTime;
    private double maxPokemonActionFrequency;
    private double minPokemonActionFrequency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movementType_FK")
    private MovementType movementType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gender_FK")
    private Gender gender;

    public double getAttackProbability() {
        return attackProbability;
    }

    public void setAttackProbability(double attackProbability) {
        this.attackProbability = attackProbability;
    }

    public int getAttackTimer() {
        return attackTimer;
    }

    public void setAttackTimer(int attackTimer) {
        this.attackTimer = attackTimer;
    }

    public double getBaseFleeRate() {
        return baseFleeRate;
    }

    public void setBaseFleeRate(double baseFleeRate) {
        this.baseFleeRate = baseFleeRate;
    }

    public double getBaseCaptureRate() {
        return baseCaptureRate;
    }

    public void setBaseCaptureRate(double baseCaptureRate) {
        this.baseCaptureRate = baseCaptureRate;
    }

    public double getCameraDistance() {
        return cameraDistance;
    }

    public void setCameraDistance(double cameraDistance) {
        this.cameraDistance = cameraDistance;
    }

    public double getCollisionRadius() {
        return collisionRadius;
    }

    public void setCollisionRadius(double collisionRadius) {
        this.collisionRadius = collisionRadius;
    }

    public double getDodgeDistance() {
        return dodgeDistance;
    }

    public void setDodgeDistance(double dodgeDistance) {
        this.dodgeDistance = dodgeDistance;
    }

    public double getDodgeProbability() {
        return dodgeProbability;
    }

    public void setDodgeProbability(double dodgeProbability) {
        this.dodgeProbability = dodgeProbability;
    }

    public double getJumpTime() {
        return jumpTime;
    }

    public void setJumpTime(double jumpTime) {
        this.jumpTime = jumpTime;
    }

    public double getMaxPokemonActionFrequency() {
        return maxPokemonActionFrequency;
    }

    public void setMaxPokemonActionFrequency(double maxPokemonActionFrequency) {
        this.maxPokemonActionFrequency = maxPokemonActionFrequency;
    }

    public double getMinPokemonActionFrequency() {
        return minPokemonActionFrequency;
    }

    public void setMinPokemonActionFrequency(double minPokemonActionFrequency) {
        this.minPokemonActionFrequency = minPokemonActionFrequency;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
