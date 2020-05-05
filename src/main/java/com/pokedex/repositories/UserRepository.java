package com.pokedex.repositories;

import com.pokedex.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User getOneUserByUserId(Long userId);

    User getUserByUserNameAndPassword(String userName, String password);

    @Query(value = "SELECT * FROM USER_POKEMON_LIST WHERE USER_ID = ?1 AND USER_POKEMON_ID = ?2", nativeQuery = true)
    Object getUserPokemonListByUserIdAndUserPokemonId(Long userId, Long userPokemonId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UDPATED_STATS SET ATTACK = ?2, DEFENSE = ?3, STAMINA = ?4 WHERE USER_POKEMON_ID = ?1", nativeQuery = true)
    void updateStatusById(Long userPokemonId, Integer attack, Integer defense, Integer stamina);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM USER_POKEMON_LIST WHERE USER_POKEMON_ID = ?1", nativeQuery = true)
    void removeCaughtPokemon(Long userPokemonId);
}
