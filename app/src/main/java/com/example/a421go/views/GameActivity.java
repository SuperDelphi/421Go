package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.a421go.R;
import com.example.a421go.controllers.BoardController;
import com.example.a421go.metier.SimpleBoard;
import com.example.a421go.models.Dice;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private SimpleBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
    }

    // Propriété
    private BoardController controller;

    private void init() {
        this.controller = BoardController.getInstance(this);
        this.board.addDice(new Dice()).addDice(new Dice()).addDice(new Dice());
    }

    private void listenRollDices() {
        Button rollBTN = (Button) findViewById(R.id.rollBTN);
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
                        controller.getCurrentPlayer(),
                        dices
                );
            }
        });
    }

}
