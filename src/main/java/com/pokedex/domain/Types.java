package com.pokedex.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Types {
    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy = "types")
    private Set<Pokemon> pokemonSet;

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
