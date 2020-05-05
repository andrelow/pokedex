package com.pokedex.services;

import com.pokedex.domain.user.User;
import com.pokedex.domain.user.UserPokemon;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean register(User user);

    boolean login(String userName, String password);

    List<UserPokemon>  getAllCaughtPokemon();

    Set<Long> addCaughtPokemon(List<String> pokemonList);

    boolean updateCaughtPokemonStatus(Long userPokemonId, Integer attack, Integer defense, Integer stamina);

    Set<Long> removeCaughtPokemon(List<Long> userPokemonIdList);

    boolean logout();
}
