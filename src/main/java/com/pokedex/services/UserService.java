package com.pokedex.services;

import com.pokedex.domain.user.User;
import com.pokedex.domain.user.UserPokemon;

import java.util.List;

public interface UserService {
    boolean register(User user);

    boolean login(String userName, String password);

    List<UserPokemon>  getAllCaughtPokemon();

    boolean addCaughtPokemon(List<String> pokemonList);

    boolean updateCaughtPokemonStatus(Long userPokemonId, Integer attack, Integer defense, Integer stamina);

    boolean removeCaughtPokemon(List<Long> userPokemonIdList);

    boolean logout();
}
