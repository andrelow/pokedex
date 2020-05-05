package com.pokedex.datamodel;

import com.pokedex.domain.*;

public class SortPokemonByStatsResponseSpec {
    private Long dex;
    private String name;
    private Stats stats;

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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
