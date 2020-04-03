package com.example.a421go.metier;

import android.view.ViewGroup;

import com.example.a421go.models.Dice;

import java.util.ArrayList;

public class SimpleBoard {
    private ViewGroup layout;
    private static SimpleBoard instance = null;
    private ArrayList<Dice> dices = new ArrayList<>();

    public SimpleBoard(ViewGroup layout) {
        this.layout = layout;
        init();
    }

    public SimpleBoard(ViewGroup layout, ArrayList<Dice> dices) {
        this.layout = layout;
        // TODO Compléter
        setDices(dices);
        init();
    }

    public static SimpleBoard getInstance() {
        if (SimpleBoard.instance == null)
            return null;

        return instance;
    }

    public static SimpleBoard getInstance(ViewGroup context) {
        if (SimpleBoard.instance == null) {
            SimpleBoard.instance = new SimpleBoard(context);
        }
        return instance;
    }

    public static SimpleBoard getInstance(ViewGroup context, ArrayList<Dice> dices) {
        if (SimpleBoard.instance == null) {
            SimpleBoard.instance = new SimpleBoard(context, dices);
        }
        return instance;
    }

    public void rollDices() {
        for (Dice dice :
                getDices()) {
            dice.roll();
        }
    }

    public void rollDices(ArrayList<Dice> dices) {
        for (Dice dice :
                getDices()) {
            if (dices.indexOf(dice) != -1) {
                dice.roll();
            }
        }
    }

    public Dice getDice(int index) {
        return getDices().get(0);
    }

    public ArrayList<Dice> getDices() {
        return this.dices;
    }

    public ArrayList<Dice> getSelectedDices() {
        ArrayList<Dice> result = new ArrayList<>();
        for (Dice dice :
                getDices()) {
            if (dice.isSelected()) result.add(dice);
        }

        return result;
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

    public SimpleBoard addDice(Dice dice) {
        getDices().add(dice);
        return this;
    }

    public void init() {
        for (Dice dice :
                getDices()) {

        }
    }

    public void update() {

    }
}
