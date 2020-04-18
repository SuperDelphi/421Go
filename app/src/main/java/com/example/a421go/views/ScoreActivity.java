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
import com.example.a421go.controllers.GameController;
import com.example.a421go.metier.PlayerComparator;
import com.example.a421go.models.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Activity affichant la liste des joueurs selon leurs vitcoires
 */
public class ScoreActivity extends AppCompatActivity {

    //Propriétés
    private ImageButton returnToWelcomeBTN;
    private LinearLayout playersListLL;
    private GameController controller;

    /**
     * Méthode utilisé lors de la création de l'activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        init();
    }

    /**
     * Fonction initialisation de la vue avec les objets graphique
     */
    private void init(){
        returnToWelcomeBTN = (ImageButton) findViewById(R.id.scoreReturnBTN);
        playersListLL = (LinearLayout) findViewById(R.id.playersListLL);
        controller = GameController.getInstance();
        listenreturnBTN();
        addPlayersList();
    }

    /**
     * Ecoute de l'événement sur le bouton returnBTN
     * Retour vers l'accueil
     */
    private void listenreturnBTN() {
        returnToWelcomeBTN.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Méthode qui permet l'affichage des joueurs contenus dans la base de données
     * en fonction de leurs scores finaux
     */
    private void addPlayersList(){
        ArrayList<Player> playersList = controller.getDatabase().getPlayers();
        for (Player p : playersList){
            TextView joueurET = new TextView(ScoreActivity.this);
            joueurET.setText(p.getName()+" #"+(playersListLL.getChildCount() + 1)+" "+p.getVictories());
            if (p.getVictories() > 1){
                joueurET.setText(joueurET.getText()+" victoires");
            } else {
                joueurET.setText(joueurET.getText()+" victoire");
            }
            joueurET.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            playersListLL.addView(joueurET);
        }
    }
}
