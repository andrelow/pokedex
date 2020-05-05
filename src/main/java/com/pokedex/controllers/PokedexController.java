package com.pokedex.controllers;

import com.pokedex.datamodel.PokemonAndStatsDTO;
import com.pokedex.datamodel.PokemonAndTypesDTO;
import com.pokedex.datamodel.PokemonNameAndDexDTO;
import com.pokedex.datamodel.ResponseSpec;
import com.pokedex.domain.Pokemon;
import com.pokedex.domain.PokemonType;
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
    public List<PokemonNameAndDexDTO> getPokemonByType(@RequestParam String type1, @RequestParam(required = false) String type2) {
        String firstTypeId = type1 != null ? PokemonType.get(type1) : null;
        String secondTypeId = type2 != null ? PokemonType.get(type2) : null;

        return pokedexService.findPokemonListByTwoTypes(firstTypeId, secondTypeId);
    }

    @GetMapping("/findByStatsGreaterThan")
    public List<PokemonAndStatsDTO> getPokemonByStatsGreaterThan(@RequestParam(required = false) Integer baseAttack,
                                                                 @RequestParam(required = false) Integer baseDefense,
                                                                 @RequestParam(required = false) Integer baseStamina) {
        return pokedexService.findPokemonListByStatusGreatherThan(baseAttack, baseDefense, baseStamina);
    }

    @GetMapping("/findByStatsLessThan")
    public List<PokemonAndStatsDTO> getPokemonByStatsLessThan(@RequestParam(required = false) Integer baseAttack,
                                                              @RequestParam(required = false) Integer baseDefense,
                                                              @RequestParam(required = false) Integer baseStamina) {
        return pokedexService.findPokemonListByStatusLessThan(baseAttack, baseDefense, baseStamina);
    }

    @GetMapping("/findAllSortByName")
    public List<PokemonNameAndDexDTO> findAllPokemonSortByName() {
        return pokedexService.findAllPokemonSortByName();
    }

    @GetMapping("/findAllSortByTypes")
    public List<PokemonAndTypesDTO> findAllPokemonSortByTypes() {
        return pokedexService.findAllPokemonSortByTypes();
    }

    @GetMapping("/findAllSortByStats")
    public List<PokemonAndStatsDTO> findAllPokemonSortByStats(@RequestParam(required = false) String sortCriteria) {
        return pokedexService.findAllPokemonSortByStats(sortCriteria);
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
