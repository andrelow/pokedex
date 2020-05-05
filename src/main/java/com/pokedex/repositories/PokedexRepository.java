package com.pokedex.repositories;

import com.pokedex.datamodel.PokemonAndStatsDTO;
import com.pokedex.datamodel.PokemonAndTypesDTO;
import com.pokedex.datamodel.PokemonNameAndDexDTO;
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

    @Query(value = "SELECT DISTINCT p.DEX AS dex, p.NAME AS name " +
            "FROM POKEMON p, POKEMON_TYPES type " +
            "WHERE p.DEX = type.DEX_ID AND " +
            "type.DEX_ID IN (SELECT DEX_ID FROM POKEMON_TYPES where (?1 is null or TYPES_ID = ?1)) " +
            "AND (?2 is null or type.TYPES_ID = ?2)", nativeQuery = true)
    List<PokemonNameAndDexDTO> findPokemonIdByTwoTypes(String type1, String type2);

    @Query(value = "SELECT p.DEX AS dex, p.NAME AS name, s.BASE_ATTACK as baseAttack, s.BASE_DEFENSE as baseDefense, s.BASE_STAMINA as baseStamina " +
            "FROM POKEMON p, STATS s " +
            "WHERE p.STATS_ID = s.STATS_ID AND " +
            "(?1 is null or s.BASE_ATTACK <= ?1) AND (?2 is null or s.BASE_DEFENSE <= ?2) " +
            "AND (?3 is null or s.BASE_STAMINA <= ?3)", nativeQuery = true)
    List<PokemonAndStatsDTO> findAllPokemonByStatsLessThan(Integer baseAttack, Integer baseDefense, Integer baseStamina);

    @Query(value = "SELECT p.DEX AS dex, p.NAME AS name, s.BASE_ATTACK as baseAttack, s.BASE_DEFENSE as baseDefense, s.BASE_STAMINA as baseStamina " +
            "FROM POKEMON p, STATS s " +
            "WHERE p.STATS_ID = s.STATS_ID AND " +
            "(?1 is null or s.BASE_ATTACK >= ?1) AND (?2 is null or s.BASE_DEFENSE >= ?2) " +
            "AND (?3 is null or s.BASE_STAMINA >= ?3)", nativeQuery = true)
    List<PokemonAndStatsDTO> findAllPokemonByStatsGreatherThan(Integer baseAttack, Integer baseDefense, Integer baseStamina);

    @Query(value = "SELECT p.DEX AS dex, p.NAME AS name, type.TYPES_ID AS type " +
            "FROM POKEMON p, POKEMON_TYPES type " +
            "WHERE p.DEX = type.DEX_ID " +
            "ORDER BY type.TYPES_ID", nativeQuery = true)
    List<PokemonAndTypesDTO> findAllPokemonSortByTypes();

    @Query(value = "SELECT p.DEX AS dex, p.NAME AS name, S.BASE_ATTACK as baseAttack, S.BASE_DEFENSE as baseDefense, S.BASE_STAMINA as baseStamina " +
            "FROM POKEMON p, STATS s " +
            "WHERE p.STATS_ID = s.STATS_ID " +
            "ORDER BY s.BASE_ATTACK DESC", nativeQuery = true)
    List<PokemonAndStatsDTO> findAllPokemonOrderByAttackStatsDesc();

    @Query(value = "SELECT p.DEX AS dex, p.NAME AS name, S.BASE_ATTACK as baseAttack, S.BASE_DEFENSE as baseDefense, S.BASE_STAMINA as baseStamina " +
            "FROM POKEMON p, STATS s " +
            "WHERE p.STATS_ID = s.STATS_ID " +
            "ORDER BY s.BASE_DEFENSE DESC", nativeQuery = true)
    List<PokemonAndStatsDTO> findAllPokemonOrderByDefenseStatsDesc();

    @Query(value = "SELECT p.DEX AS dex, p.NAME AS name, S.BASE_ATTACK as baseAttack, S.BASE_DEFENSE as baseDefense, S.BASE_STAMINA as baseStamina " +
            "FROM POKEMON p, STATS s " +
            "WHERE p.STATS_ID = s.STATS_ID " +
            "ORDER BY s.BASE_STAMINA DESC", nativeQuery = true)
    List<PokemonAndStatsDTO> findAllPokemonOrderByStaminaStatsDesc();

    @Query(value = "SELECT p.DEX AS dex, p.NAME AS name " +
            "FROM POKEMON p " +
            "ORDER BY p.NAME ASC", nativeQuery = true)
    List<PokemonNameAndDexDTO> findAllPokemonSortByName();
}
