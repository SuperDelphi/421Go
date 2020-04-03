package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a421go.R;
import com.example.a421go.controllers.BoardController;
import com.example.a421go.controllers.GameController;
import com.example.a421go.metier.SimpleBoard;
import com.example.a421go.models.Dice;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    // Propriété
    private BoardController boardController;
    private GameController gameController;
    private SimpleBoard board;
    private TextView playergameTV;
    private TextView remainingThrowsTV;
    private Button gameInfoBTN;
    private Button rollBTN;
    private LinearLayout boardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
    }

    private void init() {
        boardController = BoardController.getInstance(this);
        board.addDice(new Dice()).addDice(new Dice()).addDice(new Dice());
        playergameTV = (TextView) findViewById(R.id.playergameTV);
        remainingThrowsTV = (TextView) findViewById(R.id.remainingThrowsTV);
        gameInfoBTN = (Button) findViewById(R.id.gameInfoBTN);
        rollBTN = (Button) findViewById(R.id.rollBTN);
        boardLayout = (LinearLayout) findViewById(R.id.boardLayout);
    }

    private void listenRollDices() {
        rollBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout boardLayout = (LinearLayout)findViewById(R.id.boardLayout);
                BoardController controller = BoardController.getInstance(getApplicationContext());
                SimpleBoard board = SimpleBoard.getInstance(boardLayout);

                ArrayList<Dice> dices = new ArrayList<>();
                dices.add(board.getDice(0));
                dices.add(board.getDice(1));
                dices.add(board.getDice(2));

                controller.submitRound(
                        gameController.getCurrentRound(),
                        dices
                );

                gameController.getGame().nextPlayer();
            }
        });
    }

}
