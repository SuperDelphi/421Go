package com.example.a421go.models;

import android.view.ViewGroup;

import java.util.ArrayList;

public class BoardCouple {
    private ViewGroup layout;
    private ArrayList<Dice> dices;

    public BoardCouple(ViewGroup layout, ArrayList<Dice> dices) {
        this.layout = layout;
        this.dices = dices;
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public void setLayout(ViewGroup layout) {
        this.layout = layout;
    }

    public ArrayList<Dice> getDices() {
        return dices;
    }

    public void setDices(ArrayList<Dice> dices) {
        this.dices = dices;
    }
}
