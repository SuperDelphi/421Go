package com.example.a421go.metier;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a421go.R;
import com.example.a421go.lib.DimensionConverter;
import com.example.a421go.models.Dice;

import java.util.ArrayList;

public class SimpleBoard {
    private ViewGroup layout;
    private static SimpleBoard instance = null;
    private ArrayList<Dice> dices = new ArrayList<>();
    private int[] selectedFaceIds = {
            R.drawable.dice_1_selected,
            R.drawable.dice_2_selected,
            R.drawable.dice_3_selected,
            R.drawable.dice_4_selected,
            R.drawable.dice_5_selected,
            R.drawable.dice_6_selected
    };
    private int[] faceIds = {
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6
    };

    public SimpleBoard(ViewGroup layout) {
        this.layout = layout;
    }

    public SimpleBoard(ViewGroup layout, ArrayList<Dice> dices) {
        this.layout = layout;
        // TODO Compléter
        setDices(dices);
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
        return getDices().get(index);
    }

    public Dice getDice(View v) {
        int index = -1;
        for (int i = 0; i < getLayout().getChildCount(); i++) {
            if (getLayout().getChildAt(i).equals(v)) index = i;
        }
        if (index != -1) {
            return getDice(index);
        } else {
            return null;
        }
    }

    public ArrayList<Dice> getDices() {
        return this.dices;
    }

    public ViewGroup getLayout() {
        return layout;
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

    public void removeAllDices() {
        getDices().clear();
    }

    public SimpleBoard addDice(Dice dice) {
        getDices().add(dice);
        return this;
    }

    public Drawable getFaceDrawable(int faceNumber, boolean isSelected) {
        Context context = getLayout().getContext();
        int[] faceArray = isSelected ? this.selectedFaceIds : this.faceIds;
        Drawable face = null;
        if ((faceNumber <= faceArray.length) && (faceNumber >= 1)) {
            face = context.getDrawable(faceArray[faceNumber - 1]);
        }
        return face;
    }

    public void init() {
        // Ajout des trois dés du début
        removeAllDices();
        addDice(new Dice());
        addDice(new Dice());
        addDice(new Dice());
        updateLayout();
    }

    public void updateLayout() {
        Context context = getLayout().getContext();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                DimensionConverter.convertPixelToDP(context, 100),
                DimensionConverter.convertPixelToDP(context, 100)
        );
        ImageView tmpView;
        getLayout().removeAllViews();
        for (Dice dice :
                getDices()) {
            tmpView = new ImageView(context);
            tmpView.setLayoutParams(layoutParams);
            tmpView.setImageDrawable(getFaceDrawable(dice.getFace(), dice.isSelected()));
            getLayout().addView(tmpView);

            tmpView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDice(v).toggleSelection();
                    updateLayout();
                }
            });
        }
    }
}
