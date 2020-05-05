package com.pokedex.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CinematicMoves {
    @Id
    private String id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
