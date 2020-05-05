package com.pokedex.controllers;

import com.pokedex.datamodel.LoginRequestSpec;
import com.pokedex.datamodel.ResponseSpec;
import com.pokedex.datamodel.UpdateStatsRequestSpec;
import com.pokedex.domain.user.User;
import com.pokedex.domain.user.UserPokemon;
import com.pokedex.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public ResponseSpec register(@RequestBody User user) {
        ResponseSpec response = new ResponseSpec();

        try {
            if (userService.register(user)) {
                response.setStatus(com.pokedex.datamodel.ResponseStatus.OK);
                response.setMessage("Register successful");
            } else {
                response.setStatus(com.pokedex.datamodel.ResponseStatus.FAILED);
                response.setMessage("Register failed");
            }
        } catch (Exception e) {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.FAILED);
            response.setMessage("Register failed");
        }

        return response;
    }

    @PostMapping("/login")
    public ResponseSpec login(@RequestBody LoginRequestSpec loginRequestSpec) {
        ResponseSpec response = new ResponseSpec();
        if (userService.login(loginRequestSpec.getUserName(), loginRequestSpec.getPassword())) {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.OK);
            response.setMessage("Login successful");
        } else {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.FAILED);
            response.setMessage("Login failed");
        }

        return response;
    }

    @PostMapping("/addCaughtPokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSpec addCaughtPokemon(@RequestBody List<String> pokemonList) {
        ResponseSpec response = new ResponseSpec();
        Set<Long> addedPokemon = userService.addCaughtPokemon(pokemonList);
        if (addedPokemon.size() > 0) {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.OK);
            response.setMessage("Add Caught Pokemon successful for " + addedPokemon.toString());
        } else {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.FAILED);
            response.setMessage("Add Caught Pokemon failed");
        }

        return response;
    }

    @PostMapping("/updateCaughtPokemonStatus")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSpec updateCaughtPokemonStatus(@RequestBody UpdateStatsRequestSpec updateSpec) {
        ResponseSpec response = new ResponseSpec();
        if (userService.updateCaughtPokemonStatus(updateSpec.getUserPokemonId(), updateSpec.getAttack(),
                updateSpec.getDefense(), updateSpec.getStamina())) {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.OK);
            response.setMessage("updateCaughtPokemonStatus successful");
        } else {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.FAILED);
            response.setMessage("updateCaughtPokemonStatus failed");
        }

        return response;
    }

    @PostMapping("/removeCaughtPokemon")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSpec removeCaughtPokemon(@RequestBody List<Long> userPokemonIdList) {
        ResponseSpec response = new ResponseSpec();
        Set<Long> removedPokemon = userService.removeCaughtPokemon(userPokemonIdList);
        if (removedPokemon.size() > 0) {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.OK);
            response.setMessage("Remove Caught Pokemon successful for " + removedPokemon.toString());
        } else {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.FAILED);
            response.setMessage("Remove Caught Pokemon failed");
        }

        return response;
    }

    @GetMapping("/displayCaughtPokemon")
    public List<UserPokemon> displayCaughtPokemon() {
        return userService.getAllCaughtPokemon();
    }

    @GetMapping("/logout")
    public ResponseSpec logout() {
        ResponseSpec response = new ResponseSpec();
        if (userService.logout()) {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.OK);
            response.setMessage("Logout successful");
        } else {
            response.setStatus(com.pokedex.datamodel.ResponseStatus.FAILED);
            response.setMessage("Logout failed");
        }

        return response;
    }
}
