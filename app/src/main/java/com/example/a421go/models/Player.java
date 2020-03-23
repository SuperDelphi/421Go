package com.example.a421go.models;

public class Player {
    // Properties

    private String name;
    private int victories = 0;

    // Constructors

    public Player(String name) {
        String finalName = name;

        if (finalName == "") {
            finalName = "Ninja" + Math.floor(Math.random() * 9999);
        }

        this.name = finalName;
    }

    // Methods

    public void addVictory() {
        this.victories++;
    }

    // Getters

    public String getName() {
        return name;
    }

    public int getVictories() {
        return victories;
    }
}
