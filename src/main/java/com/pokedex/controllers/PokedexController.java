package com.pokedex.controllers;

import com.pokedex.datamodel.ResponseSpec;
import com.pokedex.domain.Pokemon;
import com.pokedex.domain.PokemonType;
import com.pokedex.domain.Types;
import com.pokedex.services.PokedexService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(PokedexController.BASE_URL)
public class PokedexController {

    public static final String BASE_URL = "/api/v1/pokedex";

    private final PokedexService pokedexService;

    public PokedexController(PokedexService pokedexService) {
        this.pokedexService = pokedexService;
    }

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokedexService.findAllPokemon();
    }

    @GetMapping("/count")
    public long getCount() {
        return pokedexService.count();
    }

    @GetMapping("/findById/{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokedexService.findById(id);
    }

    @GetMapping("/findByName/{name}")
    public List<Pokemon> getPokemonByName(@PathVariable String name) {
        return pokedexService.findByName(name);
    }

    @GetMapping("/findByType")
    public List<Pokemon> getPokemonByType(@RequestParam String type1, @RequestParam(required = false) String type2) {
        Types firstType = new Types();
        if (type1 != null) {
            System.out.println(type1);

            firstType.setName(type1);
            firstType.setId(PokemonType.get(type1));
        } else {
            return null;
        }

        Types secondType = new Types();
        if (type2 != null) {
            secondType.setName(type2);
            secondType.setId(PokemonType.get(type2));
        } else {
            return pokedexService.findPokemonListByOneType(firstType);
        }

        return pokedexService.findPokemonListByTwoTypes(firstType, secondType);
    }

    @GetMapping("/findByStats")
    public List<Pokemon> getPokemonByStats(@RequestParam(required = false) Integer baseAttack,
                                           @RequestParam(required = false) Integer baseDefense,
                                           @RequestParam(required = false) Integer baseStamina) {
        return pokedexService.findPokemonListByStatus(baseAttack, baseDefense, baseStamina);
    }

    @GetMapping("/findSorted")
    public List<Pokemon> findAllPokemonSortByTypes(@RequestParam(required = false) String sortCriteria) {
        List<Pokemon> pokemonList;
        switch (sortCriteria) {
            case "types":
                pokemonList = pokedexService.findAllPokemonSortByTypes();
                break;
            default:
                pokemonList = pokedexService.findAllPokemonSortByStats(sortCriteria);
        }

        return pokemonList;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSpec savePokemon(@RequestBody Pokemon pokemon) {
        ResponseSpec response = new ResponseSpec();

        try {
            pokedexService.savePokemon(pokemon);

            response.setStatus(com.pokedex.datamodel.ResponseStatus.OK);
            response.setMessage("Successfully saved all Pokemons");
        } catch (Exception e) {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.FAILED);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @PostMapping("/saveAll")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSpec saveAllPokemon(@RequestBody List<Pokemon> pokemonList) {
        ResponseSpec response = new ResponseSpec();

        try {
            pokedexService.saveAllPokemon(pokemonList);

            response.setStatus(com.pokedex.datamodel.ResponseStatus.OK);
            response.setMessage("Successfully saved all Pokemons");
        } catch (Exception e) {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.FAILED);
            response.setMessage(e.getMessage());
        }

        return response;
    }

}
