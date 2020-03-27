package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a421go.R;
import com.example.a421go.controllers.GameController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // Properties
    private Button newGameBTN;
    private Button rankBTN;
    private GameController controller;
    private Intent intent = null;

    /**
     * Initialisation des liens avec les objets graphiques
     */
    private void init(){
        newGameBTN = (Button) findViewById(R.id.newGameBTN);
        rankBTN = (Button) findViewById(R.id.rankBTN);
        this.controller = GameController.getInstance();
        listenNewGameBTN();
        listenRankBTN();
    }

    /**
     * Ecoute de l'événement sur le bouton newGameBTN
     */
    private void listenNewGameBTN(){
        ((Button) findViewById(R.id.newGameBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, NewGameActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton rankBTN
     */
    private void listenRankBTN(){
        ((Button) findViewById(R.id.rankBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
}
