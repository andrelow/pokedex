package com.pokedex.services;

import com.pokedex.datamodel.PokemonAndStatsDTO;
import com.pokedex.datamodel.PokemonAndTypesDTO;
import com.pokedex.datamodel.PokemonNameAndDexDTO;
import com.pokedex.domain.Pokemon;

import java.util.List;

public interface PokedexService {

    Pokemon findById(Long id);

    List<Pokemon> findByName(String name);

    List<Pokemon> findAllPokemon();

    List<PokemonNameAndDexDTO> findPokemonListByTwoTypes(String firstTypeId, String secondTypeId);

    List<PokemonAndStatsDTO> findPokemonListByStatusGreatherThan(Integer baseAttack, Integer baseDefense, Integer baseStamina);

    List<PokemonAndStatsDTO> findPokemonListByStatusLessThan(Integer baseAttack, Integer baseDefense, Integer baseStamina);

    List<PokemonNameAndDexDTO> findAllPokemonSortByName();

    List<PokemonAndTypesDTO> findAllPokemonSortByTypes();

    List<PokemonAndStatsDTO> findAllPokemonSortByStats(String sortedBy);

    void savePokemon(Pokemon pokemon);

    void saveAllPokemon(List<Pokemon> pokemon);

    long count();
}
