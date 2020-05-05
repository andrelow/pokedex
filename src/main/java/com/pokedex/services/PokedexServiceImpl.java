package com.pokedex.services;

import com.pokedex.domain.Pokemon;
import com.pokedex.domain.Types;
import com.pokedex.repositories.PokedexRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<Pokemon> findPokemonListByOneType(Types type) {
        return pokedexRepository.findAllPokemonByTypes(type);
    }

    @Override
    public List<Pokemon> findPokemonListByTwoTypes(Types type1, Types type2) {
        List<Long> dexIdList = pokedexRepository.findPokemonIdByTwoTypes(type1.getId(), type2.getId());
        return pokedexRepository.findAllPokemonByDexIn(dexIdList);
    }

    @Override
    public List<Pokemon> findPokemonListByStatus(Integer baseAttack, Integer baseDefense, Integer baseStamina) {
        List<Long> dexIdList = pokedexRepository.findAllPokemonByStats(baseAttack, baseDefense, baseStamina);
        return pokedexRepository.findAllPokemonByDexIn(dexIdList);
    }

    @Override
    public List<Pokemon> findAllPokemonSortByTypes() {
        List<Long> dexIdList = pokedexRepository.findAllDistinctPokemonByOrderByTypesAsc();

        List<Long> dexIdWithoutDuplicate = getListWithoutDuplicate(dexIdList);
        return pokedexRepository.findAllPokemonByDexIn(dexIdWithoutDuplicate);
    }

    @Override
    public List<Pokemon> findAllPokemonSortByStats(String sortedBy) {
        List<Long> dexIdList = new ArrayList<>();

        switch(sortedBy) {
            case "attack":
                dexIdList = pokedexRepository.findAllPokemonOrderByAttackStatsDesc();
                break;
            case "defense":
                dexIdList = pokedexRepository.findAllPokemonOrderByDefenseStatsDesc();
                break;
            case "stamina":
                dexIdList = pokedexRepository.findAllPokemonOrderByStaminaStatsDesc();
                break;
        }

        List<Long> dexIdWithoutDuplicate = getListWithoutDuplicate(dexIdList);
        return pokedexRepository.findAllPokemonByDexIn(dexIdWithoutDuplicate);
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

    private List<Long> getListWithoutDuplicate(List<Long> itemList) {
        List<Long> newList = new ArrayList<>();
        for (Long item : itemList) {
            if (!newList.contains(item)) {
                newList.add(item);
            }
        }

        return newList;
    }
}
