package com.pokedex.services;

import com.pokedex.domain.Pokemon;
import com.pokedex.domain.user.UpdatedStats;
import com.pokedex.domain.user.User;
import com.pokedex.domain.user.UserPokemon;
import com.pokedex.repositories.PokedexRepository;
import com.pokedex.repositories.UserPokemonRepository;
import com.pokedex.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PokedexRepository pokedexRepository;
    private final UserPokemonRepository userPokemonRepository;
    private User activeUser = null;

    public UserServiceImpl(UserRepository userRepository,
                           PokedexRepository pokedexRepository,
                           UserPokemonRepository userPokemonRepository) {
        this.userRepository = userRepository;
        this.pokedexRepository = pokedexRepository;
        this.userPokemonRepository = userPokemonRepository;
    }

    @Override
    public boolean register(User user) {
        User result = userRepository.save(user);
        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean login(String userName, String password) {
        activeUser = userRepository.getUserByUserNameAndPassword(userName, password);
        if (activeUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<UserPokemon> getAllCaughtPokemon() {
        List<UserPokemon> userPokemonList = userPokemonRepository.getAllUserPokemonByUser(activeUser);
        return userPokemonList;
    }

    @Override
    public Set<Long> addCaughtPokemon(List<String> pokemonList) {
        Set<Long> addedPokemonDex = new HashSet<>();
        if (activeUser != null) {
            List<Pokemon> caughtPokemon = pokedexRepository.findAllPokemonByNameIn(pokemonList);

            for (Pokemon pokemon : caughtPokemon) {
                addedPokemonDex.add(pokemon.getDex());
                userPokemonRepository.save(new UserPokemon(pokemon, new UpdatedStats(), activeUser));
            }
        }

        return addedPokemonDex;
    }

    @Override
    public boolean updateCaughtPokemonStatus(Long userPokemonId, Integer attack, Integer defense, Integer stamina) {
        UserPokemon userPokemon = userPokemonRepository.findOneByUserAndUserPokemonId(activeUser, userPokemonId);
        if (userPokemon == null) {
            return false;
        }

        if (userPokemon.getUpdatedStats() == null) {
            userPokemon.setUpdatedStats(new UpdatedStats());
        }

        UpdatedStats updatedStats = userPokemon.getUpdatedStats();
        updatedStats.setAttack(attack);
        updatedStats.setDefense(defense);
        updatedStats.setStamina(stamina);

        userPokemonRepository.save(userPokemon);
        return true;
    }

    @Override
    public Set<Long> removeCaughtPokemon(List<Long> userPokemonIdList) {
        Set<Long> removedPokemon = new HashSet<>();
        for (Long userPokemonId : userPokemonIdList) {
            if (userPokemonRepository.findOneByUserAndUserPokemonId(activeUser, userPokemonId) == null) {
                continue;
            }
            removedPokemon.add(userPokemonId);
            userPokemonRepository.deleteById(userPokemonId);
        }

        return removedPokemon;
    }

    @Override
    public boolean logout() {
        if (activeUser != null) {
            activeUser = null;
            return true;
        }
        return false;
    }
}
