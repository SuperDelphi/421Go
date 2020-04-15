package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a421go.R;
import com.example.a421go.controllers.BoardController;
import com.example.a421go.controllers.GameController;
import com.example.a421go.lib.VibratorHelper;
import com.example.a421go.metier.SimpleBoard;
import com.example.a421go.models.Dice;
import com.example.a421go.models.Round;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    // Propriété
    private BoardController boardController;
    private GameController gameController;
    private TextView playergameTV, remainingThrowsTV;
    private ImageButton gameInfoBTN, rollBTN, finishBTN;
    private LinearLayout boardLayout, reserveLayout;
    private View menuInfoFragment;
    private Intent intent = null;
    private Round lastRound = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
    }

    private void init() {
        gameController = GameController.getInstance();
        boardController = BoardController.getInstance();
        boardLayout = (LinearLayout) findViewById(R.id.boardLayout);
        reserveLayout = (LinearLayout) findViewById(R.id.reserveLayout);
        SimpleBoard.getInstance(boardLayout, reserveLayout).addDice(new Dice()).addDice(new Dice()).addDice(new Dice());
        playergameTV = (TextView) findViewById(R.id.playergameTV);
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
    }

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

    private void listenFinish() {
        finishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout boardLayout = (LinearLayout) findViewById(R.id.boardLayout);
                BoardController controller = BoardController.getInstance();
                SimpleBoard board = SimpleBoard.getInstance(boardLayout, reserveLayout);
                controller.submitRound(
                        gameController.getCurrentRound(),
                        board.getDicesFromEverywhere()
                );
                gameController.getGame().nextPlayer();
                board.init();
                gameController.checkGameState(GameActivity.this);
                update();
            }
        });
    }

    private void update() {
        // Affichage

        this.remainingThrowsTV.setText(getText(R.string.remaining_throws) + " " + gameController.getGame().getCurrentRound().getState().getThrowsLeft());
        String textTemplate = (String) getText(R.string.your_turn);
        this.playergameTV.setText(gameController.getInstance().getCurrentPlayer().getName() + textTemplate);

        // S'il reste des lancers & qu'un lancer a déjà été effectué
        if (gameController.getThrowsLeft() > 0 && gameController.getThrowsLeft() < gameController.getMaxThrowsPerRound()) {
            finishBTN.setVisibility(View.VISIBLE);
            rollBTN.setImageResource(R.drawable.roll_dices_again_btn);
            // Si aucun lancer n'a été effectué
        } else if (gameController.getThrowsLeft() == gameController.getMaxThrowsPerRound()) {
            rollBTN.setVisibility(View.VISIBLE);
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
     */
    private void listenGameInfoBTN(){
        ((ImageButton) findViewById(R.id.gameInfoBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                menuInfoFragment.setVisibility(View.VISIBLE);
            }
        });
    }
}
