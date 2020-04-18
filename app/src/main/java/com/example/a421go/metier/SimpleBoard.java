package com.example.a421go.metier;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a421go.R;
import com.example.a421go.controllers.GameController;
import com.example.a421go.lib.DimensionConverter;
import com.example.a421go.lib.VibratorHelper;
import com.example.a421go.models.BoardCouple;
import com.example.a421go.models.Dice;
import com.example.a421go.views.GameActivity;

import java.util.ArrayList;

public class SimpleBoard {
    private GameActivity gameActivity;
    private ViewGroup layout;
    private static SimpleBoard instance = null;
    private ArrayList<Dice> dices = new ArrayList<>();
    private ReserveBoard reserve;
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

    public SimpleBoard(GameActivity gameActivity, ViewGroup boardLayout, ViewGroup reserveLayout) {
        this.gameActivity = gameActivity;
        this.layout = boardLayout;
        this.reserve = new ReserveBoard(reserveLayout);
    }

    public static SimpleBoard getInstance() {
        if (SimpleBoard.instance == null)
            return null;

        return instance;
    }

    public static SimpleBoard getInstance(GameActivity gameActivity, ViewGroup boardLayout, ViewGroup reserveLayout) {
        if (SimpleBoard.instance == null) {
            SimpleBoard.instance = new SimpleBoard(gameActivity, boardLayout, reserveLayout);
        }
        return instance;
    }

    public static void destroy() {
        SimpleBoard.instance = null;
    }

    public void rollDices() {
        GameController gameController = GameController.getInstance();
        boolean isFirstRoll = gameController.getThrowsLeft() == gameController.getMaxThrowsPerRound();

        ArrayList<Dice> dicesToRoll = isFirstRoll ? getDicesFromEverywhere() : getSelectedDicesFromEverywhere();

        if (!isFirstRoll) {
            store(getUnselectedDicesFromEverywhere());
        }

        retrieve(getSelectedDicesFromEverywhere());

        for (Dice dice :
                dicesToRoll) {
            dice.roll();
        }
    }

//    public void rollDices(ArrayList<Dice> dices) {
//        for (Dice dice :
//                getDices()) {
//            if (dices.indexOf(dice) != -1) {
//                dice.roll();
//            }
//        }
//    }

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

    public Dice getDiceFromEverywhere(View v) {
        Dice dice = getDice(v);

        if (dice != null) {
            return dice;
        } else {
            int index = -1;
            for (int i = 0; i < getReserve().getLayout().getChildCount(); i++) {
                if (getReserve().getLayout().getChildAt(i).equals(v)) index = i;
            }
            if (index != -1) {
                return getReserve().getDice(index);
            } else {
                return null;
            }
        }
    }

    public ArrayList<Dice> getDices() {
        return this.dices;
    }

    public ArrayList<Dice> getDicesFromEverywhere() {
        ArrayList<Dice> allDices = new ArrayList<>();
        allDices.addAll(getDices());
        allDices.addAll(getReserve().getDices());

        return allDices;
    }

    public ViewGroup getLayout() {
        return layout;
    }

    public ReserveBoard getReserve() {
        return this.reserve;
    }

    public ArrayList<Dice> getSelectedDicesFromEverywhere() {
        ArrayList<Dice> result = new ArrayList<>();
        for (Dice dice :
                getDicesFromEverywhere()) {
            if (dice.isSelected()) result.add(dice);
        }
        return result;
    }

    public ArrayList<Dice> getUnselectedDicesFromEverywhere() {
        ArrayList<Dice> result = new ArrayList<>();
        for (Dice dice :
                getDicesFromEverywhere()) {
            if (!dice.isSelected()) result.add(dice);
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

    public boolean removeDices(ArrayList<Dice> dices) {
        return getDices().removeAll(dices);
    }

    public void removeAllDices() {
        getDices().clear();
    }

    public SimpleBoard addDice(Dice dice) {
        getDices().add(dice);
        return this;
    }

    public void addDices(ArrayList<Dice> dices) {
        getDices().addAll(dices);
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

    public void store(ArrayList<Dice> dices) {
        removeDices(getReserve().addDices(dices));
        updateLayouts();
    }

    public void retrieve(ArrayList<Dice> dices) {
        ArrayList<Dice> addedDices = new ArrayList<>();
        for (Dice dice :
                dices) {
            if (!getDices().contains(dice)) {
                getDices().add(dice);
                addedDices.add(dice);
            }
        }
        getReserve().removeDices(addedDices);
    }

    public void selectAll() {
        for (Dice dice :
                getDicesFromEverywhere()) {
            dice.select();
        }

        updateLayouts();
    }

    public void deselectAll() {
        for (Dice dice :
                getDicesFromEverywhere()) {
            dice.deselect();
        }

        updateLayouts();
    }

    public void init() {
        // Ajout des trois dés du début
        removeAllDices();
        getReserve().clear();
        addDice(new Dice());
        addDice(new Dice());
        addDice(new Dice());

        updateLayouts();
    }

    public void updateLayouts() {
        ArrayList<BoardCouple> boardCouples = new ArrayList<>();
        boardCouples.add(new BoardCouple(getLayout(), getDices()));
        boardCouples.add(new BoardCouple(getReserve().getLayout(), getReserve().getDices()));

        for (BoardCouple couple:
             boardCouples) {

            final Context context = couple.getLayout().getContext();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    DimensionConverter.convertPixelToDP(context, 100),
                    DimensionConverter.convertPixelToDP(context, 100)
            );
            ImageView tmpView;
            couple.getLayout().removeAllViews();
            for (Dice dice :
                    couple.getDices()) {
                tmpView = new ImageView(context);
                tmpView.setLayoutParams(layoutParams);
                tmpView.setImageDrawable(getFaceDrawable(dice.getFace(), dice.isSelected()));
                couple.getLayout().addView(tmpView);

                GameController gameController = GameController.getInstance();
                if (gameController.getThrowsLeft() < gameController.getMaxThrowsPerRound() && gameController.getThrowsLeft() > 0) {
                    tmpView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            VibratorHelper.vibrate(25);
                            getDiceFromEverywhere(v).toggleSelection();
                            updateLayouts();
                            gameActivity.checkDiceSelection();
                        }
                    });
                }
            }

        }
    }
}
