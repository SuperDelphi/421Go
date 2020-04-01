package com.example.a421go.metier;

import android.graphics.Canvas;

import com.example.a421go.models.Dice;

import java.util.ArrayList;

public class BoardCanvas extends Canvas {
    private ArrayList<Dice> dices = new ArrayList<>();

    public BoardCanvas() {
        super();
    }

    public BoardCanvas(ArrayList<Dice> dices) {
        this();
        this.dices = dices;
    }

    public void rollDices(ArrayList<Dice> dices) {
//        for (Dice dice :
//                getDices()) {
//
//        }
    }

    public Dice getDice(int index) {
        return getDices().get(0);
    }

    public ArrayList<Dice> getDices() {
        return this.dices;
    }

    public Dice removeDice(int index) {
        return getDices().remove(index);
    }

    public boolean removeDice(Dice dice) {
        return getDices().remove(dice);
    }

    public void addDice(Dice dice) {
        getDices().add(dice);
    }
}
