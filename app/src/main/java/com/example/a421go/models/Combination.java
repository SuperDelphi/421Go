package com.example.a421go.models;

import java.util.ArrayList;

/**
 * Représente une combinaison.
 */
public class Combination {

    // Properties

    private String name;
    private int points;
    private ArrayList<Dice> dicesList;

    // Constructors

    public Combination(ArrayList<Dice> dicesList) {
        this.dicesList = dicesList;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public ArrayList<Dice> getDicesList() {
        return dicesList;
    }

    //Setters
    public void setPoints(int points) {
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }
}
