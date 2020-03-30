package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.a421go.models.Player;

import java.util.ArrayList;
import java.util.Date;

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
     * Et création des listner sur les boutons de l'application
     */
    private void init(){
        returnBTN = (ImageButton) findViewById(R.id.returnBTN);
        addPalyerBTN = (Button) findViewById(R.id.addPlayerBTN);
        startGameBTN = (Button) findViewById(R.id.startGameBTN);
        targetScoreET = (EditText) findViewById(R.id.targetScoreET);
        addPlayerET = (EditText) findViewById(R.id.addPlayerET);
        listPlayersLL = (LinearLayout) findViewById(R.id.listPlayersLL);
        this.controller = GameController.getInstance(this);
        targetScoreET.setText("25");
        listenaddPlayerBTN();
        listenreturnBTN();
        listenStartGamerBTN();
    }

    /**
     * Ecoute de l'événement sur le bouton returnBTN
     * Retour vers l'accueil
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
     * Ajout un nom de joueur dans la liste listPlayersLL si il y a du texte
     * dans addPlayerET et si il y a moins de 4 joueurs dans la liste
     * Si les conditions ne sont plus remplis, des toats envoie un message explicatif à l'utilsateur
     */
    private void listenaddPlayerBTN(){
        ((Button) findViewById(R.id.addPlayerBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String playerName = addPlayerET.getText().toString().trim();
                if (!playerName.equals("") && listPlayersLL.getChildCount() < 4){
                    int place = listPlayersLL.getChildCount()+1;
                    TextView joueurET = new TextView(NewGameActivity.this);
                    joueurET.setText(place+". "+addPlayerET.getText().toString().trim());
                    joueurET.setTooltipText(addPlayerET.getText().toString().trim());
                    listPlayersLL.addView(joueurET);
                    addPlayerET.setText("");
                } else {
                    if (listPlayersLL.getChildCount() >= 4){
                        Toast.makeText(NewGameActivity.this, "Le nombre de joueurs est limité à 4.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NewGameActivity.this, "Veuillez saisir un nom valide.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton startGameBTN
     * Démarre l'activité GameActivity
     */
    private void listenStartGamerBTN(){
        ((Button) findViewById(R.id.startGameBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Date now = new Date();
                int targetScore = Integer.parseInt(targetScoreET.getText().toString());
                ArrayList<Player> playerslist = new ArrayList<Player>();
                for (int i = 0; i < listPlayersLL.getChildCount(); i++){
                    playerslist.add(new Player((String) listPlayersLL.getChildAt(i).getTooltipText()));
                }
                controller.playGame(targetScore, now, playerslist);
                intent = new Intent(NewGameActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
