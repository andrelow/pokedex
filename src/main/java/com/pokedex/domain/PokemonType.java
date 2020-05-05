package com.pokedex.domain;

import java.util.HashMap;
import java.util.Map;

public enum PokemonType {
    POKEMON_TYPE_FIRE("Fire"),
    POKEMON_TYPE_WATER("Water"),
    POKEMON_TYPE_GRASS("Grass"),
    POKEMON_TYPE_NORMAL("Normal"),
    POKEMON_TYPE_BUG("Bug"),
    POKEMON_TYPE_POISON("Poison"),
    POKEMON_TYPE_FLYING("Flying"),
    POKEMON_TYPE_DARK("Dark"),
    POKEMON_TYPE_PSYCHIC("Psychic"),
    POKEMON_TYPE_ICE("Ice"),
    POKEMON_TYPE_STEEL("Steel"),
    POKEMON_TYPE_GROUND("Ground"),
    POKEMON_TYPE_FAIRY("Fairy"),
    POKEMON_TYPE_FIGHTING("Fighting"),
    POKEMON_TYPE_ROCK("Rock"),
    POKEMON_TYPE_GHOST("Ghost"),
    POKEMON_TYPE_DRAGON("Dragon");

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
