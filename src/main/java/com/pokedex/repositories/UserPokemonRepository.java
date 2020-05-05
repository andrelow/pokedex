package com.pokedex.repositories;

import com.pokedex.domain.user.User;
import com.pokedex.domain.user.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPokemonRepository extends JpaRepository<UserPokemon, Long> {
    UserPokemon findOneByUserAndUserPokemonId(User user, Long userPokemonId);

    List<UserPokemon> getAllUserPokemonByUser(User user);
}
