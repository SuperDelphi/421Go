package com.example.a421go.models;

/**
 * Représente une combinaison.
 */
public enum Combination {
    _421("421"),
    FICHE("Fiche"),
    BARAQUE("Baraque"),
    SUITE("Suite");

    // Properties

    private String name;

    // Constructors

    private Combination(String name) {
        this.name = name;
    }

    // Getters

    public String getName() {
        return name;
    }
}
