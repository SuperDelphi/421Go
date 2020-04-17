package com.example.a421go.metier;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a421go.controllers.GameController;
import com.example.a421go.lib.DimensionConverter;
import com.example.a421go.lib.VibratorHelper;
import com.example.a421go.models.Dice;

import java.util.ArrayList;

public class ReserveBoard {
    private ViewGroup layout;
    private ArrayList<Dice> dices = new ArrayList<>();

    public ReserveBoard(ViewGroup layout) {
        this.layout = layout;
    }

    public Dice getDice(int index) {
        return getDices().get(index);
    }

    public ArrayList<Dice> getDices() {
        return dices;
    }

    public void addDice(Dice dice) {
        getDices().add(dice);
    }

    public void addDices(ArrayList<Dice> dices) {
        getDices().addAll(dices);
    }

    public void setDices(ArrayList<Dice> dices) {
        this.dices = dices;
    }

    public boolean removeDice(Dice dice) {
        return getDices().remove(dice);
    }

    public boolean removeDices(ArrayList<Dice> dices) {
        return getDices().removeAll(dices);
    }

    public void clear() {
        getDices().clear();
    }

    public ViewGroup getLayout() {
        return this.layout;
    }
}
