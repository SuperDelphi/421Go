package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a421go.R;
import com.example.a421go.controllers.GameController;

public class NewGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        init();
    }

    // Properties
    private ImageButton returnBTN;
    private Button addPalyerBTN;
    private Button startGameBTN;
    private EditText targetScoreET;
    private EditText addPlayerET;
    private LinearLayout listPlayersLL;
    private GameController controller;
    private Intent intent = null;

    /**
     * Initialisation des liens avec les objets graphiques
     */
    private void init(){
        returnBTN = (ImageButton) findViewById(R.id.returnBTN);
        addPalyerBTN = (Button) findViewById(R.id.addPlayerBTN);
        startGameBTN = (Button) findViewById(R.id.startGameBTN);
        targetScoreET = (EditText) findViewById(R.id.targetScoreET);
        addPlayerET = (EditText) findViewById(R.id.addPlayerET);
        listPlayersLL = (LinearLayout) findViewById(R.id.listPlayersLL);
        this.controller = GameController.getInstance();
        targetScoreET.setText("25");
        listenaddPlayerBTN();
        listenreturnBTN();
        listenStartGamerBTN();
    }

    /**
     * Ecoute de l'événement sur le bouton returnBTN
     */
    private void listenreturnBTN(){
        ((ImageView) findViewById(R.id.returnBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(NewGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton addPlayerBTN
     */
    private void listenaddPlayerBTN(){
        ((Button) findViewById(R.id.addPlayerBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String playerName = addPlayerET.getText().toString().trim();
                if (!playerName.equals("") && listPlayersLL.getChildCount() < 4){
                    int place = listPlayersLL.getChildCount()+1;
                    TextView joueurET = new TextView(NewGameActivity.this);
                    joueurET.setText(place+". "+addPlayerET.getText().toString().trim());
                    listPlayersLL.addView(joueurET);
                    addPlayerET.setText("");
                } else {
                    if (listPlayersLL.getChildCount() >= 4){
                        Toast.makeText(NewGameActivity.this, "Le nombre de joueurs est limité à 4", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NewGameActivity.this, "Aucun nom de joueur saisie", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton startGameBTN
     */
    private void listenStartGamerBTN(){
        ((Button) findViewById(R.id.startGameBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                controller.playGame();
                intent = new Intent(NewGameActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
