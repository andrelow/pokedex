package com.pokedex.domain;

import java.util.HashMap;
import java.util.Map;

public enum PokemonType {
    POKEMON_TYPE_GRASS("Grass"),
    POKEMON_TYPE_POISON("Poison");

    private String name;

    PokemonType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static final Map<String, PokemonType> lookupMap = new HashMap<>();

    static {
        for (PokemonType type : PokemonType.values()) {
            lookupMap.put(type.getName(), type);
        }
    }

    public static String get(String name) {
        return lookupMap.get(name).toString();
    }
}
