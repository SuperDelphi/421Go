package com.example.a421go.metier;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.a421go.models.Dice;

import java.util.ArrayList;

public class BoardCanvas extends Canvas {
    private int diceSize = 50;
    private int backgroundColor = Color.WHITE;
    private ArrayList<Dice> dices = new ArrayList<>();

    public BoardCanvas() {
        super();
        update();
    }

    public BoardCanvas(ArrayList<Dice> dices) {
        this.dices = dices;
        update();
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

    public void setDices(ArrayList<Dice> dices) {
        this.dices = dices;
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

    public void init() {
        for (Dice dice :
                getDices()) {

        }
    }

    public void update() {
        this.drawColor(Color.WHITE);
    }
}
