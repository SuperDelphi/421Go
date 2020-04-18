package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a421go.R;
import com.example.a421go.controllers.GameController;

/**
 * Activity affichant l'accueil de l'application
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Méthode utilisé lors de la création de l'activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // Properties
    private ImageButton newGameBTN;
    private ImageButton rankBTN;
    private ImageButton quitGameBTN;
    private ImageButton settingBTN;
    private GameController controller;
    private Intent intent = null;

    /**
     * Initialisation des liens avec les objets graphiques
     */
    private void init(){
        newGameBTN = (ImageButton) findViewById(R.id.newGameBTN);
        rankBTN = (ImageButton) findViewById(R.id.rankBTN);
        quitGameBTN = (ImageButton) findViewById(R.id.exitBTN);
        settingBTN = (ImageButton) findViewById(R.id.settingBTN);
        this.controller = GameController.getInstance();
        listenNewGameBTN();
        listenRankBTN();
        listenExitBTN();
        listensettingBTN();
    }

    /**
     * Ecoute de l'événement sur le bouton newGameBTN
     * Démarre l'activité {@link NewGameActivity}
     */
    private void listenNewGameBTN(){
        ((ImageButton) findViewById(R.id.newGameBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, NewGameActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton rankBTN
     * Démarre l'activty {@link ScoreActivity}
     */
    private void listenRankBTN(){
        ((ImageButton) findViewById(R.id.rankBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton exitBTN
     */
    private void listenExitBTN(){
        ((ImageButton) findViewById(R.id.exitBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton settingBTN
     * Affiche les créateurs de l'application
     */
    private void listensettingBTN(){
        ((ImageButton) findViewById(R.id.settingBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Application créée par Simon Breil & Gabriel Krawczyk", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
