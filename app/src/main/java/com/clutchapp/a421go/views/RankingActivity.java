package com.clutchapp.a421go.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clutchapp.a421go.R;
import com.clutchapp.a421go.controllers.GameController;
import com.clutchapp.a421go.metier.PlayerComparator;
import com.clutchapp.a421go.models.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Activity affichant le classemnt de fin d'une partie
 */
public class RankingActivity extends AppCompatActivity {

    // Propriété
    private GameController controller;
    private TextView winnerTV;
    private LinearLayout listLoserLL;
    private ImageButton restartGameBTN;
    private ImageButton quitGameBTN;
    private Intent intent = null;


    /**
     * Méthode utilisé lors de la création de l'activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        init();
    }

    /**
     * Initialisation des liens avec les objets graphiques, triage des rounds
     * dans l'ordre décroissant et  insertion des informations de fin de partie
     */
    private void init() {
        controller = GameController.getInstance();
        winnerTV = (TextView) findViewById(R.id.winnerTV);
        listLoserLL = (LinearLayout) findViewById(R.id.listLoserLL);
        restartGameBTN = (ImageButton) findViewById(R.id.restartGameBTN);
        quitGameBTN = (ImageButton) findViewById(R.id.quitGameBTN);
        ArrayList<Player> playersList = controller.playersRanking();
        Collections.sort(playersList, new PlayerComparator());
        winnerTV.setText(playersList.get(0).getName()+" #1 - "+ playersList.get(0).getScoreFinal()+" pts");
        for (int i = 1; i < controller.getGame().getPlayersList().size(); i++){
            TextView joueurET = new TextView(RankingActivity.this);
            joueurET.setText(playersList.get(i).getName()+" #"+(listLoserLL.getChildCount()+2)+" - "+playersList.get(i).getScoreFinal()+" pts");
            listLoserLL.addView(joueurET);
        }
        GameController.getInstance().addVictoryToWinner(playersList.get(0));
        listenQuitGameBTN();
        listenRestartGameBTN();
    }

    /**
     * Ecoute de l'événement sur le bouton restartGameBTN
     * Permet de relancer une partie avec les mêmes caractéritiques que celle qui vient d'être jouée
     */
    private void listenRestartGameBTN(){
        ((ImageButton) findViewById(R.id.restartGameBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                GameController.getInstance().replayLastGame(RankingActivity.this);
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton quitGameBTN
     * Permet de retourner vers l'accueil {@link MainActivity}
     */
    private void listenQuitGameBTN(){
        ((ImageButton) findViewById(R.id.quitGameBTN)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(RankingActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
