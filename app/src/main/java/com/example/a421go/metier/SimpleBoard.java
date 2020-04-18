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

/**
 * Représente le plateau de jeu.
 */
public class SimpleBoard {
    private static SimpleBoard instance = null;
    private GameActivity gameActivity;
    private ViewGroup layout;
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

    /**
     * Constructeur de la classe.
     *
     * @param gameActivity  l'instance de la classe {@link GameActivity}
     * @param boardLayout   le layout ({@link ViewGroup}) qui permet d'accueillir les dés du plateau de jeu
     * @param reserveLayout le layout ({@link ViewGroup}) qui permet d'accueillir les dés de la réserve
     */
    public SimpleBoard(GameActivity gameActivity, ViewGroup boardLayout, ViewGroup reserveLayout) {
        this.gameActivity = gameActivity;
        this.layout = boardLayout;
        this.reserve = new ReserveBoard(reserveLayout);
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     *
     * @return L'unique instance de la classe.
     */
    public static SimpleBoard getInstance() {
        if (SimpleBoard.instance == null)
            return null;

        return instance;
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     *
     * @param gameActivity  une instance de la classe {@link GameActivity}
     * @param boardLayout   le layout ({@link ViewGroup}) qui permet d'accueillir les dés du plateau de jeu
     * @param reserveLayout le le layout ({@link ViewGroup}) qui permet d'accueillir les dés de la réserve
     * @return L'unique instance de la classe.
     */
    public static SimpleBoard getInstance(GameActivity gameActivity, ViewGroup boardLayout, ViewGroup reserveLayout) {
        if (SimpleBoard.instance == null) {
            SimpleBoard.instance = new SimpleBoard(gameActivity, boardLayout, reserveLayout);
        }
        return instance;
    }

    /**
     * Détruit l'unique instance de la classe.
     */
    public static void destroy() {
        SimpleBoard.instance = null;
    }

    /**
     * Lance les dés sélectionnés.
     * Range les dés non sélectionnés par l'utilisateur dans la réserve.
     */
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

    /**
     * @param index la position du dé
     * @return le dé situé à la position spécifiée
     */
    public Dice getDice(int index) {
        return getDices().get(index);
    }

    /**
     * @param v la {@link View} représentant le dé
     * @return le dé que la {@link View} spécifiée représente
     */
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

    /**
     * Équivalent à la méthode getDice, mais cherche aussi dans la réserve.
     *
     * @param v la {@link View} représentant le dé
     * @return le dé que la {@link View} spécifiée représente
     */
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

    /**
     * @return la liste de tous les dés du plateau de jeu
     */
    public ArrayList<Dice> getDices() {
        return this.dices;
    }

    /**
     * Définit la liste des dés à partir d'une nouvelle liste
     *
     * @param dices la nouvelle liste des dés
     */
    public void setDices(ArrayList<Dice> dices) {
        this.dices = dices;
    }

    /**
     * Équivalent à la méthode getDices, mais cherche aussi dans la réserve.
     *
     * @return la liste de tous les dés, qu'ils soient sur le plateau de jeu ou dans la réserve
     */
    public ArrayList<Dice> getDicesFromEverywhere() {
        ArrayList<Dice> allDices = new ArrayList<>();
        allDices.addAll(getDices());
        allDices.addAll(getReserve().getDices());

        return allDices;
    }

    /**
     * @return le layout ({@link ViewGroup}) utilisé pour représenter le plateau de jeu
     */
    public ViewGroup getLayout() {
        return layout;
    }

    /**
     * @return la réserve ({@link ReserveBoard})
     */
    public ReserveBoard getReserve() {
        return this.reserve;
    }

    /**
     * @return la liste de tous les dés sélectionnés, qu'ils soient sur le plateau de jeu ou dans la réserve
     */
    public ArrayList<Dice> getSelectedDicesFromEverywhere() {
        ArrayList<Dice> result = new ArrayList<>();
        for (Dice dice :
                getDicesFromEverywhere()) {
            if (dice.isSelected()) result.add(dice);
        }
        return result;
    }

    /**
     * @return la liste de tous les dés non sélectionnés, qu'ils soient sur le plateau de jeu ou dans la réserve
     */
    public ArrayList<Dice> getUnselectedDicesFromEverywhere() {
        ArrayList<Dice> result = new ArrayList<>();
        for (Dice dice :
                getDicesFromEverywhere()) {
            if (!dice.isSelected()) result.add(dice);
        }

        return result;
    }

    /**
     * Supprime un dé du plateau de jeu.
     *
     * @param index la position du dé
     * @return le dé supprimé
     */
    public Dice removeDice(int index) {
        return getDices().remove(index);
    }

    /**
     * Supprime un dé du plateau de jeu.
     *
     * @param dice le dé à supprimer
     * @return TRUE si le dé a correctement été supprimé, sinon FALSE
     */
    public boolean removeDice(Dice dice) {
        return getDices().remove(dice);
    }

    /**
     * Supprime des dés du plateau de jeu.
     *
     * @param dices la liste des dés à supprimer
     * @return TRUE si la suppression s'est effectuée correctement, sinon FALSE
     */
    public boolean removeDices(ArrayList<Dice> dices) {
        return getDices().removeAll(dices);
    }

    /**
     * Supprime tous les dés du plateau de jeu.
     */
    public void removeAllDices() {
        getDices().clear();
    }

    /**
     * Ajoute un dé au plateau de jeu.
     *
     * @param dice le dé à ajouter
     * @return l'unique instance de {@link SimpleBoard}
     */
    public SimpleBoard addDice(Dice dice) {
        getDices().add(dice);
        return this;
    }

    /**
     * Ajoute des dés au plateau de jeu.
     *
     * @param dices la liste des dés à ajouter
     */
    public void addDices(ArrayList<Dice> dices) {
        getDices().addAll(dices);
    }

    /**
     * @param faceNumber le numéro de la face
     * @param isSelected TRUE pour la version "sélectionnée", sinon FALSE
     * @return un {@link Drawable} représentant la face spécifiée
     */
    public Drawable getFaceDrawable(int faceNumber, boolean isSelected) {
        Context context = getLayout().getContext();
        int[] faceArray = isSelected ? this.selectedFaceIds : this.faceIds;
        Drawable face = null;
        if ((faceNumber <= faceArray.length) && (faceNumber >= 1)) {
            face = context.getDrawable(faceArray[faceNumber - 1]);
        }
        return face;
    }

    /**
     * Transfère des dés du plateau de jeu vers la réserve.
     *
     * @param dices la liste des dés à transférer
     */
    public void store(ArrayList<Dice> dices) {
        removeDices(getReserve().addDices(dices));
        updateLayouts();
    }

    /**
     * Récupère des dés de la réserve.
     *
     * @param dices la liste des dés à récupérer
     */
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

    /**
     * Sélectionne tous les dés présents.
     */
    public void selectAll() {
        for (Dice dice :
                getDicesFromEverywhere()) {
            dice.select();
        }

        updateLayouts();
    }

    /**
     * Désélectionne tous les dés présents.
     */
    public void deselectAll() {
        for (Dice dice :
                getDicesFromEverywhere()) {
            dice.deselect();
        }

        updateLayouts();
    }

    /**
     * Initialise le plateau de jeu.
     */
    public void init() {
        // Ajout des trois dés du début
        removeAllDices();
        getReserve().clear();
        addDice(new Dice());
        addDice(new Dice());
        addDice(new Dice());

        updateLayouts();
    }

    /**
     * Met à jour l'affiche du plateau de jeu et de la réserve.
     */
    public void updateLayouts() {
        ArrayList<BoardCouple> boardCouples = new ArrayList<>();
        boardCouples.add(new BoardCouple(getLayout(), getDices()));
        boardCouples.add(new BoardCouple(getReserve().getLayout(), getReserve().getDices()));

        for (BoardCouple couple :
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
