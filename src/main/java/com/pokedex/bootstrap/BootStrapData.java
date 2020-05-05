package com.pokedex.bootstrap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pokedex.domain.Pokemon;
import com.pokedex.repositories.PokedexRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PokedexRepository pokedexRepository;

    public BootStrapData(PokedexRepository pokedexRepository) {
        this.pokedexRepository = pokedexRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        System.out.println("=========================================================================================");
        System.out.println("Loading Pokemon Data...");

        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data_pokemon.json")));
        List<Pokemon> pokemonList = new Gson().fromJson(br, new TypeToken<List<Pokemon>>(){}.getType());
        pokedexRepository.saveAll(pokemonList);

        System.out.println("Total Pokemon data in Pokedex: " + pokedexRepository.count());
    }
}
