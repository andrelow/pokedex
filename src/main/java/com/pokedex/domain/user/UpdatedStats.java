package com.pokedex.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UpdatedStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long updatedStatsId;

    private Integer attack;
    private Integer defense;
    private Integer stamina;

    public UpdatedStats() {

    }

    public UpdatedStats(Integer attack, Integer defense, Integer stamina) {
        this.attack = attack;
        this.defense = defense;
        this.stamina = stamina;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }
}
