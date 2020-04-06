package com.example.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a421go.R;
import com.example.a421go.controllers.GameController;
import com.example.a421go.metier.RoundComparator;
import com.example.a421go.models.Round;
import com.example.a421go.models.RoundGroup;

import java.util.ArrayList;
import java.util.Collections;

public class RankingActivity extends AppCompatActivity {

    // Propriété
    private GameController controller;
    private TextView winnerTV;
    private LinearLayout listLoserLL;
    private ImageButton restartGameBTN;
    private ImageButton quitGameBTN;
    private Intent intent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        init();
    }

    /**
     * Initialisation des liens avec les objets graphiques, triage des rounds dans l'ordre décroissant et  insertion des informations de fin de partie
     */
    private void init() {
        controller = GameController.getInstance(this);
        winnerTV = (TextView) findViewById(R.id.winnerTV);
        listLoserLL = (LinearLayout) findViewById(R.id.listLoserLL);
        restartGameBTN = (ImageButton) findViewById(R.id.restartGameBTN);
        quitGameBTN = (ImageButton) findViewById(R.id.quitGameBTN);
        ArrayList<RoundGroup> roundsGroupsList = controller.getGame().getRoundsGroupsList();
        //Collections.sort(roundsList, new RoundComparator());
        winnerTV.setText("nom_du_vainqueur #1 - nombre_de_points pts");
        for (int i = 1; i < controller.getGame().getPlayersList().size(); i++){
            TextView joueurET = new TextView(RankingActivity.this);
            joueurET.setText("nom_du_perdant #"+(listLoserLL.getChildCount()+2)+" - nombre_de_points pts");
            listLoserLL.addView(joueurET);
        }
        listenQuitGameBTN();
        listenRestartGameBTN();
    }

    /**
     * Ecoute de l'événement sur le bouton restartGameBTN
     */
    private void listenRestartGameBTN(){
        ((ImageButton) findViewById(R.id.restartGameBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(RankingActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton quitGameBTN
     */
    private void listenQuitGameBTN(){
        ((ImageButton) findViewById(R.id.quitGameBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(RankingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
