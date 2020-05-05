package com.pokedex.controllers;

import com.pokedex.datamodel.LoginRequestSpec;
import com.pokedex.datamodel.UpdateStatsRequestSpec;
import com.pokedex.domain.user.User;
import com.pokedex.domain.user.UserPokemon;
import com.pokedex.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/api/v1/user";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequestSpec loginRequestSpec) {
        return userService.login(loginRequestSpec.getUserName(), loginRequestSpec.getPassword());
    }

    @PostMapping("/addCaughtPokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addCaughtPokemon(@RequestBody List<String> pokemonList) {
        return userService.addCaughtPokemon(pokemonList);
    }

    @PostMapping("/updateCaughtPokemonStatus")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean updateCaughtPokemonStatus(@RequestBody UpdateStatsRequestSpec updateSpec) {
        return userService.updateCaughtPokemonStatus(updateSpec.getUserPokemonId(), updateSpec.getAttack(),
                updateSpec.getDefense(), updateSpec.getStamina());
    }

    @PostMapping("/removeCaughtPokemon")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeCaughtPokemon(@RequestBody List<Long> userPokemonIdList) {
        return userService.removeCaughtPokemon(userPokemonIdList);
    }

    @GetMapping("/displayCaughtPokemon")
    public List<UserPokemon> displayCaughtPokemon() {
        return userService.getAllCaughtPokemon();
    }

    @GetMapping("/logout")
    public boolean logout() {
        return userService.logout();
    }
}
