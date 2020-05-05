package com.pokedex.repositories;

import com.pokedex.domain.Pokemon;
import com.pokedex.domain.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PokedexRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findAllPokemonByName(String name);

    List<Pokemon> findAllPokemonByNameIn(List<String> nameList);

    List<Pokemon> findAllPokemonByDexIn(List<Long> dexIdList);

    List<Pokemon> findAllPokemonByTypes(Types types);

    @Query(value = "SELECT DISTINCT DEX_ID FROM POKEMON_TYPES WHERE DEX_ID IN (SELECT DEX_ID FROM POKEMON_TYPES where TYPES_ID = ?1) " +
            "AND TYPES_ID = ?2", nativeQuery = true)
    List<Long> findPokemonIdByTwoTypes(String type1, String type2);

    @Query(value = "SELECT DISTINCT DEX FROM POKEMON WHERE STATS_ID IN (SELECT STATS_ID FROM STATS WHERE " +
            "(?1 is null or BASE_ATTACK = ?1) AND (?2 is null or BASE_DEFENSE = ?2) " +
            "AND (?3 is null or BASE_STAMINA = ?3))", nativeQuery = true)
    List<Long> findAllPokemonByStats(Integer baseAttack, Integer baseDefense, Integer baseStamina);

    //@Query(value = "SELECT DEX, NAME,  FROM POKEMON P, FAMILY F,  " +
    //        "WHERE STATS_ID IN (SELECT STATS_ID FROM STATS WHERE " +
    //        "(?1 is null or BASE_ATTACK = ?1) AND (?2 is null or BASE_DEFENSE = ?2) " +
    //        "AND (?3 is null or BASE_STAMINA = ?3))", nativeQuery = true)
    //List<SortPokemonResponseSpec> findAllPokemonByStats(boolean greatherThan, Integer baseAttack, Integer baseDefense, Integer baseStamina);

    @Query(value = "SELECT DEX_ID FROM POKEMON_TYPES ORDER BY TYPES_ID", nativeQuery = true)
    List<Long> findAllDistinctPokemonByOrderByTypesAsc();

    @Query(value = "SELECT DISTINCT DEX FROM POKEMON WHERE STATS_ID IN (SELECT STATS_ID FROM STATS ORDER BY BASE_ATTACK DESC)", nativeQuery = true)
    List<Long> findAllPokemonOrderByAttackStatsDesc();

    @Query(value = "SELECT DISTINCT DEX FROM POKEMON WHERE STATS_ID IN (SELECT STATS_ID FROM STATS ORDER BY BASE_DEFENSE DESC)", nativeQuery = true)
    List<Long> findAllPokemonOrderByDefenseStatsDesc();

    @Query(value = "SELECT DISTINCT DEX FROM POKEMON WHERE STATS_ID IN (SELECT STATS_ID FROM STATS ORDER BY BASE_STAMINA DESC)", nativeQuery = true)
    List<Long> findAllPokemonOrderByStaminaStatsDesc();
}
