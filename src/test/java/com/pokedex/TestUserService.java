package com.pokedex;

import com.pokedex.domain.Pokemon;
import com.pokedex.domain.user.User;
import com.pokedex.domain.user.UserPokemon;
import com.pokedex.repositories.PokedexRepository;
import com.pokedex.repositories.UserPokemonRepository;
import com.pokedex.repositories.UserRepository;
import com.pokedex.services.UserService;
import com.pokedex.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class TestUserService {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PokedexRepository pokedexRepository;

    @Mock
    private UserPokemonRepository userPokemonRepository;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository, pokedexRepository, userPokemonRepository);
    }

    @Test
    public void testRegister_success() {
        when(userRepository.save(any(User.class))).thenReturn(new User());

        boolean result = userService.register(new User());
        Assert.assertEquals(true, result);
    }

    @Test
    public void testRegister_failed() {
        when(userRepository.save(any(User.class))).thenReturn(null);

        boolean result = userService.register(new User());
        Assert.assertEquals(false, result);
    }

    @Test
    public void testLogin_success() {
        when(userRepository.getUserByUserNameAndPassword(anyString(), anyString())).thenReturn(new User());

        boolean result = userService.login("a", "b");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testLogin_failed() {
        when(userRepository.getUserByUserNameAndPassword(anyString(), anyString())).thenReturn(null);

        boolean result = userService.login("a", "b");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testAddCaughtPokemon_success() {
        // login first
        when(userRepository.getUserByUserNameAndPassword(anyString(), anyString())).thenReturn(new User());
        userService.login("a", "b");

        List<Pokemon> pokemonList = new ArrayList<>();
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setDex(1L);
        pokemonList.add(pokemon1);
        Pokemon pokemon2 = new Pokemon();
        pokemon2.setDex(3L);
        pokemonList.add(pokemon2);

        when(pokedexRepository.findAllPokemonByNameIn(any(ArrayList.class))).thenReturn(pokemonList);

        List<String> pokemonName = new ArrayList<>();
        Set<Long> actualResult = userService.addCaughtPokemon(pokemonName);

        Set<Long> result = new HashSet<>();
        result.add(1L);
        result.add(3L);
        Assert.assertEquals(result, actualResult);
    }

    @Test
    public void testAddCaughtPokemon_failed_withoutLogin() {
        List<Pokemon> pokemonList = new ArrayList<>();
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setDex(1L);
        pokemonList.add(pokemon1);
        Pokemon pokemon2 = new Pokemon();
        pokemon2.setDex(3L);
        pokemonList.add(pokemon2);

        List<String> pokemonName = new ArrayList<>();
        Set<Long> actualResult = userService.addCaughtPokemon(pokemonName);

        Set<Long> result = new HashSet<>();
        result.add(1L);
        result.add(3L);
        Assert.assertNotEquals(result, actualResult);
    }

    @Test
    public void testUpdateCaughtPokemonStatus_success() {
        // login first
        when(userRepository.getUserByUserNameAndPassword(anyString(), anyString())).thenReturn(new User());
        userService.login("a", "b");

        UserPokemon userPokemon = new UserPokemon();
        when(userPokemonRepository.findOneByUserAndUserPokemonId(any(User.class), anyLong())).thenReturn(new UserPokemon());

        boolean result = userService.updateCaughtPokemonStatus(1L, null, null, null);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testUpdateCaughtPokemonStatus_failed() {
        boolean result = userService.updateCaughtPokemonStatus(1L, null, null, null);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testRemoveCaughtPokemon_success() {
        // login first
        when(userRepository.getUserByUserNameAndPassword(anyString(), anyString())).thenReturn(new User());
        userService.login("a", "b");

        when(userPokemonRepository.findOneByUserAndUserPokemonId(any(User.class), anyLong())).thenReturn(new UserPokemon());

        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(3L);
        Set<Long> actualResult = userService.removeCaughtPokemon(idList);

        Set<Long> result = new HashSet<>();
        result.add(1L);
        result.add(3L);
        Assert.assertEquals(result, actualResult);
    }

    @Test
    public void testRemoveCaughtPokemon_failed_withoutLogin() {
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(3L);
        Set<Long> actualResult = userService.removeCaughtPokemon(idList);

        Set<Long> result = new HashSet<>();
        result.add(1L);
        result.add(3L);
        Assert.assertNotEquals(result, actualResult);
    }

    @Test
    public void testLogout_success() {
        when(userRepository.getUserByUserNameAndPassword(anyString(), anyString())).thenReturn(new User());
        userService.login("a", "b");

        boolean result = userService.logout();
        Assert.assertEquals(true, result);
    }

    @Test
    public void testLogout_failed() {
        boolean result = userService.logout();
        Assert.assertEquals(false, result);
    }
}
