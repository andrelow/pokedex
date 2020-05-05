package com.pokedex.services;

import com.pokedex.datamodel.PokemonAndStatsDTO;
import com.pokedex.datamodel.PokemonAndTypesDTO;
import com.pokedex.datamodel.PokemonNameAndDexDTO;
import com.pokedex.domain.Pokemon;
import com.pokedex.repositories.PokedexRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokedexServiceImpl implements PokedexService {

    private final PokedexRepository pokedexRepository;

    public PokedexServiceImpl(PokedexRepository pokedexRepository) {
        this.pokedexRepository = pokedexRepository;
    }

    @Override
    public Pokemon findById(Long id) {
        return pokedexRepository.findById(id).get();
    }

    @Override
    public List<Pokemon> findByName(String name) {
        return pokedexRepository.findAllPokemonByName(name);
    }

    @Override
    public List<Pokemon> findAllPokemon() {
        return pokedexRepository.findAll();
    }

    @Override
    public List<PokemonNameAndDexDTO> findPokemonListByTwoTypes(String firstTypeId, String secondTypeId) {
        return pokedexRepository.findPokemonIdByTwoTypes(firstTypeId, secondTypeId);
    }

    @Override
    public List<PokemonAndStatsDTO> findPokemonListByStatusGreatherThan(Integer baseAttack, Integer baseDefense, Integer baseStamina) {
        return pokedexRepository.findAllPokemonByStatsGreatherThan(baseAttack, baseDefense, baseStamina);
    }

    @Override
    public List<PokemonAndStatsDTO> findPokemonListByStatusLessThan(Integer baseAttack, Integer baseDefense, Integer baseStamina) {
        return pokedexRepository.findAllPokemonByStatsLessThan(baseAttack, baseDefense, baseStamina);
    }

    @Override
    public List<PokemonNameAndDexDTO> findAllPokemonSortByName() {
        return pokedexRepository.findAllPokemonSortByName();
    }

    @Override
    public List<PokemonAndTypesDTO> findAllPokemonSortByTypes() {
        return pokedexRepository.findAllPokemonSortByTypes();
    }

    @Override
    public List<PokemonAndStatsDTO> findAllPokemonSortByStats(String sortedBy) {
        List<PokemonAndStatsDTO> response = new ArrayList<>();

        switch(sortedBy) {
            case "attack":
                response = pokedexRepository.findAllPokemonOrderByAttackStatsDesc();
                break;
            case "defense":
                response = pokedexRepository.findAllPokemonOrderByDefenseStatsDesc();
                break;
            case "stamina":
                response = pokedexRepository.findAllPokemonOrderByStaminaStatsDesc();
                break;
        }

        return response;
    }

    @Override
    public void savePokemon(Pokemon pokemon) {
        pokedexRepository.save(pokemon);
    }

    @Override
    public long count() {
        return pokedexRepository.count();
    }

    @Override
    public void saveAllPokemon(List<Pokemon> pokemonList) {
        pokedexRepository.saveAll(pokemonList);
    }
}
