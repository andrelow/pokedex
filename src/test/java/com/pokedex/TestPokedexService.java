package com.pokedex;

import com.pokedex.datamodel.PokemonAndStatsDTO;
import com.pokedex.domain.user.User;
import com.pokedex.repositories.PokedexRepository;
import com.pokedex.repositories.UserPokemonRepository;
import com.pokedex.repositories.UserRepository;
import com.pokedex.services.PokedexService;
import com.pokedex.services.PokedexServiceImpl;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class TestPokedexService {
    private PokedexService pokedexService;

    @Mock
    private PokedexRepository pokedexRepository;

    @Before
    public void setUp() {
        pokedexService = new PokedexServiceImpl(pokedexRepository);
    }

    @Test
    public void testFindAllPokemonSortByStatsAttack_success(){
        List<PokemonAndStatsDTO> result = new ArrayList<>();
        when(pokedexRepository.findAllPokemonOrderByAttackStatsDesc()).thenReturn(result);

        List<PokemonAndStatsDTO> response = pokedexService.findAllPokemonSortByStats("attack");
        Assert.assertEquals(result, response);
    }

    @Test
    public void testFindAllPokemonSortByStatsDefense_success(){
        List<PokemonAndStatsDTO> result = new ArrayList<>();
        when(pokedexRepository.findAllPokemonOrderByAttackStatsDesc()).thenReturn(result);

        List<PokemonAndStatsDTO> response = pokedexService.findAllPokemonSortByStats("defense");
        Assert.assertEquals(result, response);
    }

    @Test
    public void testFindAllPokemonSortByStatsStamina_success(){
        List<PokemonAndStatsDTO> result = new ArrayList<>();
        when(pokedexRepository.findAllPokemonOrderByAttackStatsDesc()).thenReturn(result);

        List<PokemonAndStatsDTO> response = pokedexService.findAllPokemonSortByStats("stamina");
        Assert.assertEquals(result, response);
    }

    @Test
    public void testFindAllPokemonSortByStats_failed(){
        when(pokedexRepository.findAllPokemonOrderByAttackStatsDesc()).thenReturn(null);

        List<PokemonAndStatsDTO> response = pokedexService.findAllPokemonSortByStats("def");
        Assert.assertEquals(new ArrayList<>(), response);
    }
}
