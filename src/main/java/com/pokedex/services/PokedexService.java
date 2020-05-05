package com.pokedex.services;

import com.pokedex.domain.Pokemon;
import com.pokedex.domain.Types;

import java.util.List;
import java.util.Set;

public interface PokedexService {

    Pokemon findById(Long id);

    List<Pokemon> findByName(String name);

    List<Pokemon> findAllPokemon();

    List<Pokemon> findPokemonListByOneType(Types type);

    List<Pokemon> findPokemonListByTwoTypes(Types type1, Types type2);

    List<Pokemon> findPokemonListByStatus(Integer baseAttack, Integer baseDefense, Integer baseStamina);

    List<Pokemon> findAllPokemonSortByTypes();

    List<Pokemon> findAllPokemonSortByStats(String sortedBy);

    void savePokemon(Pokemon pokemon);

    void saveAllPokemon(List<Pokemon> pokemon);

    long count();
}
