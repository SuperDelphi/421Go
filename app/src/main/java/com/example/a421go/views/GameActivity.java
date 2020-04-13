package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.a421go.metier.SimpleBoard;
import com.example.a421go.models.Dice;
import com.example.a421go.models.Game;
import com.example.a421go.models.Round;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    // Propriété
    private BoardController boardController;
    private GameController gameController;
    private TextView playergameTV;
    private TextView remainingThrowsTV;
    private ImageButton gameInfoBTN;
    private ImageButton rollBTN;
    private ImageButton finishBTN;
    private LinearLayout boardLayout;
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
        SimpleBoard.getInstance(boardLayout).addDice(new Dice()).addDice(new Dice()).addDice(new Dice());
        playergameTV = (TextView) findViewById(R.id.playergameTV);
        remainingThrowsTV = (TextView) findViewById(R.id.remainingThrowsTV);
        gameInfoBTN = (ImageButton) findViewById(R.id.gameInfoBTN);
        rollBTN = (ImageButton) findViewById(R.id.rollBTN);
        finishBTN = (ImageButton) findViewById(R.id.finishBTN);
        boardLayout = (LinearLayout) findViewById(R.id.boardLayout);

        // Affichage
        remainingThrowsTV.setText(R.string.remaining_throws);

        // Dés
        SimpleBoard.getInstance().init();

        update();

        listenRollDices();
        listenFinish();
    }

    private void listenRollDices() {
        rollBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                SimpleBoard board = SimpleBoard.getInstance(boardLayout);
                controller.submitRound(
                        gameController.getCurrentRound(),
                        board.getDices()
                );
                gameController.getGame().nextPlayer();
                board.init();
                lastRound = gameController.getGame().getCurrentRoundGroup().getRoundsList().get(gameController.getGame().getCurrentRoundGroup().getRoundsList().size() - 1);
                if (lastRound.getCombination() != null) {
                    intent = new Intent(GameActivity.this, RankingActivity.class);
                    startActivity(intent);
                }

                update();
            }
        });
    }

    private void update() {
        // Affichage

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
        SimpleBoard.getInstance().updateLayout();
    }
}
