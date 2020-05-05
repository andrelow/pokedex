package com.pokedex.datamodel;

public class UpdateStatsRequestSpec {
    private Long userPokemonId;
    private Integer attack;
    private Integer defense;
    private Integer stamina;

    public Long getUserPokemonId() {
        return userPokemonId;
    }

    public void setUserPokemonId(Long userPokemonId) {
        this.userPokemonId = userPokemonId;
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
