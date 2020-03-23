package com.example.a421go.models;

public class Round {
    // Properties

    private static int roundNumber = 0;
    private int gain;
    private Combination combination;

    // Constructors

    public Round() {
        roundNumber++;
    }

    // Getters & Setters

    public static int getRoundNumber() {
        return roundNumber;
    }

    public int getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public Combination getCombination() {
        return combination;
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
    }
}
