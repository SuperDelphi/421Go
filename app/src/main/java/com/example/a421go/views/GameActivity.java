package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a421go.R;
import com.example.a421go.controllers.BoardController;
import com.example.a421go.controllers.GameController;
import com.example.a421go.lib.VibratorHelper;
import com.example.a421go.metier.SimpleBoard;
import com.example.a421go.models.Dice;
import com.example.a421go.models.Player;
import com.example.a421go.models.Round;

import java.util.ArrayList;

/**
 * Activity affichant le jeu du 421 et son plateau
 */
public class GameActivity extends AppCompatActivity {

    // Propriété
    private BoardController boardController;
    private GameController gameController;
    private TextView remainingThrowsTV;
    private ImageButton gameInfoBTN, rollBTN, finishBTN;
    private LinearLayout boardLayout, reserveLayout;
    private View menuInfoFragment;
    private Intent intent = null;
    private Round lastRound = null;

    /**
     * Méthode utilisé lors de la création de l'activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
    }

    /**
     * Initialisation des liens avec les objets graphiques
     * Et création des listner sur les boutons de l'application
     */
    private void init() {
        gameController = GameController.getInstance();
        boardController = BoardController.getInstance();
        boardLayout = (LinearLayout) findViewById(R.id.boardLayout);
        reserveLayout = (LinearLayout) findViewById(R.id.reserveLayout);
        SimpleBoard.getInstance(this, boardLayout, reserveLayout).addDice(new Dice()).addDice(new Dice()).addDice(new Dice());
        remainingThrowsTV = (TextView) findViewById(R.id.remainingThrowsTV);
        gameInfoBTN = (ImageButton) findViewById(R.id.gameInfoBTN);
        rollBTN = (ImageButton) findViewById(R.id.rollBTN);
        finishBTN = (ImageButton) findViewById(R.id.finishBTN);
        boardLayout = (LinearLayout) findViewById(R.id.boardLayout);
        menuInfoFragment = (View) findViewById(R.id.menuInfoFragment);
        menuInfoFragment.setVisibility(View.INVISIBLE);

        // Dés
        SimpleBoard.getInstance().init();

        update();

        listenRollDices();
        listenFinish();
        listenGameInfoBTN();
        listenGameInfoFragment();
    }

    /**
     * Ecoute de l'événement sur le bouton rollDices
     * Lancement des dés
     */
    private void listenRollDices() {
        rollBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VibratorHelper.vibrate(50);
                boardController.roll();
                gameController.throwsSubstract();
                update();
            }
        });
    }

    /**
     *  Méthode appelée lorsque l'on clique sur l'écran
     *  Sélection des dés
     */
    public void checkDiceSelection() {
        SimpleBoard board = SimpleBoard.getInstance();
        // Si un lancer a déjà été effectué
        if (gameController.getThrowsLeft() != gameController.getMaxThrowsPerRound()) {
            // Si aucun dé n'est sélectionné
            if (board.getSelectedDicesFromEverywhere().size() == 0) {
                rollBTN.setImageResource(R.drawable.roll_dices_again_disabled_btn);
                rollBTN.setClickable(false);
                // Sinon
            } else {
                rollBTN.setImageResource(R.drawable.roll_dices_again_enabled_btn);
                rollBTN.setClickable(true);
            }
        }

    }

    /**
     * Ecoute de l'événement sur le bouton finishBTN
     * Conclure le tour du joueur en cours
     */
    private void listenFinish() {
        finishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout boardLayout = (LinearLayout) findViewById(R.id.boardLayout);
                BoardController controller = BoardController.getInstance();
                SimpleBoard board = SimpleBoard.getInstance();
                Round currentRound = gameController.getCurrentRound();
                controller.submitRound(
                        gameController.getCurrentRound(),
                        board.getDicesFromEverywhere()
                );
                if (currentRound.getCombination().getName() == "Autre"){
                    Toast.makeText(GameActivity.this, "Aucune combinaison obtenue - 1 point", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GameActivity.this, currentRound.getCombination().getName()+" obtenu - "+ currentRound.getCombination().getPoints()+" points", Toast.LENGTH_SHORT).show();
                }
                gameController.getGame().nextPlayer();
                board.init();
                gameController.getDatabase().addRound(currentRound, gameController.getGame());
                gameController.checkGameState(GameActivity.this);
                update();
            }
        });
    }

    /**
     * Méthode qui permet de mettre à jour l'affichage du plateau de jeu durant la partie
     */
    private void update() {
        // Affichage
        String playerName = gameController.getInstance().getCurrentPlayer().getName();
        this.remainingThrowsTV.setText("Tour de "+playerName + " - "
                + getText(R.string.remaining_throws) + " " + gameController.getGame().getCurrentRound().getState().getThrowsLeft());

        // S'il reste des lancers & qu'un lancer a déjà été effectué
        if (gameController.getThrowsLeft() > 0 && gameController.getThrowsLeft() < gameController.getMaxThrowsPerRound()) {
            finishBTN.setVisibility(View.VISIBLE);
            rollBTN.setImageResource(R.drawable.roll_dices_again_disabled_btn);
            rollBTN.setClickable(false);
            // Si aucun lancer n'a été effectué
        } else if (gameController.getThrowsLeft() == gameController.getMaxThrowsPerRound()) {
            rollBTN.setVisibility(View.VISIBLE);
            rollBTN.setClickable(true);
            finishBTN.setVisibility(View.INVISIBLE);
            rollBTN.setImageResource(R.drawable.roll_dices_btn);
            // Si tous les lancers ont été effectués
        } else {
            rollBTN.setVisibility(View.INVISIBLE);
            finishBTN.setVisibility(View.VISIBLE);
        }

        // Dés

        ArrayList<Dice> dices = boardController.getDices();
        SimpleBoard.getInstance().deselectAll();
        SimpleBoard.getInstance().updateLayouts();
    }

    /**
     * Ecoute de l'événement sur le bouton gameInfoBTN
     * Affichage des scores actuels des joueurs ainsi que des combinaisons possibles du jeu
     */
    private void listenGameInfoBTN() {
        ((ImageButton) findViewById(R.id.gameInfoBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                LinearLayout rankPlayersList = (LinearLayout) findViewById(R.id.rankPlayersLL);
                rankPlayersList.removeAllViews();

                for (Player p : gameController.playersRanking()){
                    TextView joueurET = new TextView(GameActivity.this);
                    joueurET.setText(p.getName()+" - "+p.getScoreFinal()+" pts");
                    joueurET.setTextColor(Color.WHITE);
                    joueurET.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    /*if (rankPlayersList.getChildCount() == 0) {
                        joueurET.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.little_corona,0,  0);
                    }*/
                    rankPlayersList.addView(joueurET);
                }
                gameController.reInitGlobalScore();
                menuInfoFragment.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Ecoute de l'événement sur le fragment gameInfoFragment
     * Enlever l'affichage des infos de jeu
     */
    private void listenGameInfoFragment() {
        menuInfoFragment.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                menuInfoFragment.setVisibility(View.INVISIBLE);
            }
        });
    }
}
