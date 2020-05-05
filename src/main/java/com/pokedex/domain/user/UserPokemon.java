package com.pokedex.domain.user;

import com.pokedex.domain.Pokemon;

import javax.persistence.*;

@Entity
public class UserPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPokemonId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dexId")
    private Pokemon caughtPokemon;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "updatedStatsId")
    private UpdatedStats updatedStats;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;

    public UserPokemon() {

    }

    public UserPokemon(Pokemon caughtPokemon, UpdatedStats updatedStats, User user) {
        this.caughtPokemon = caughtPokemon;
        this.updatedStats = updatedStats;
        this.user = user;
    }

    public Long getUserPokemonId() {
        return userPokemonId;
    }

    public void setUserPokemonId(Long userPokemonId) {
        this.userPokemonId = userPokemonId;
    }

    public Pokemon getCaughtPokemon() {
        return caughtPokemon;
    }

    public void setCaughtPokemon(Pokemon caughtPokemon) {
        this.caughtPokemon = caughtPokemon;
    }

    public UpdatedStats getUpdatedStats() {
        return updatedStats;
    }

    public void setUpdatedStats(UpdatedStats updatedStats) {
        this.updatedStats = updatedStats;
    }
}
