package com.pokedex.bootstrap;

import com.pokedex.repositories.PokedexRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PokedexRepository pokedexRepository;

    public BootStrapData(PokedexRepository pokedexRepository) {
        this.pokedexRepository = pokedexRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading Pokemon Data");
/*
        Pokemon pokemon = new Pokemon();
        pokemon.setDex(1L);
        pokemon.setName("Pikachu");

        List<Double> animationTimeList = new ArrayList<>();
        animationTimeList.add(1.25);
        pokemon.setAnimationTime(animationTimeList);

        BuddySize buddySize = new BuddySize();
        buddySize.setId("medium");
        buddySize.setName("BUDDY_MEDIUM");
        pokemon.setBuddySize(buddySize);

        *//*List<CinematicMoves> cinematicMovesList = new ArrayList<>();
        CinematicMoves cm = new CinematicMoves();
        cm.setId("Sludge Bomb");
        cm.setName("SLUDGE_BOMB");
        cinematicMovesList.add(cm);
        pokemon.setCinematicMoves(cinematicMovesList);*//*

        pokedexRepository.save(pokemon);

        System.out.println("Pokemon saved: " + pokedexRepository.count());*/

    }
}
