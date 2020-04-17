package com.example.a421go.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.a421go.metier.SQLiteManager;

import java.util.Date;

/**
 * Représente la base de données du jeu.
 */
public class GameDatabase {
    private String name = "gameDB.sqlite";
    private int version = 1;
    private SQLiteManager manager;
    private SQLiteDatabase content;

    /**
     * Le constructeur de la classe.
     * @param context le contexte.
     */
    public GameDatabase(Context context) {
        manager = new SQLiteManager(context, name, null, version);
        content = manager.getWritableDatabase();
    }

    /**
     * Ajout un joueur à la base de données s'il n'existe pas
     * @param player
     */
    public void addPlayer(Player player) {
        content = manager.getWritableDatabase();
        String req;
        req = "SELECT JOUEUR.NOM FROM JOUEUR WHERE NOM = '"+player.getName()+"';";
        Cursor cursor = content.rawQuery(req, null);
        if (!cursor.moveToFirst()){
            req = "INSERT INTO JOUEUR (NOM) VALUES ('"+player.getName()+"')";
            content.execSQL(req);
        }
    }

    /**
     * Ajout un jeu
     * @param game
     */
    public void addGame(Game game){
        content = manager.getWritableDatabase();
        String req = "INSERT INTO PARTIE (DATE_CREATION, TARGET_SCORE) VALUES ('"+ new Date() +"', "+game.getTargetScore()+")";
        content.execSQL(req);
    }

    /**
     * Ajout les informations d'un nouveau tour d'un jeu
     * @param round
     */
    public void addRound(Round round, Game game){
        //Paramêtre globale de la méthode
        content = manager.getWritableDatabase();
        String req;
        //Récupération de l'id de la dernière partie
        req = "SELECT PARTIE.ID_PARTIE FROM PARTIE ORDER BY ID_PARTIE DESC";
        Cursor gameCursor = content.rawQuery(req, null);
        gameCursor.moveToFirst();
        int id_partie = gameCursor.getInt(gameCursor.getColumnIndex("ID_PARTIE"));
        //Récupération du numéro de la manche
        int num_manche = game.getRoundsGroupsList().size();
        //Récupération de l'id du joueur
        Log.i("var", "NOmdu joueur : " + round.getPlayer().getName());
        req = "SELECT ID_JOUEUR FROM JOUEUR WHERE NOM = '"+ round.getPlayer().getName()+"';";
        Cursor playerCursor = content.rawQuery(req, null);
        playerCursor.moveToNext();
        int id_joueur = playerCursor.getInt(playerCursor.getColumnIndex("ID_JOUEUR"));
        //Requête qui ajout le tour
        req = "INSERT INTO TOUR (ID_PARTIE, NUM_MANCHE, ID_JOUEUR, GAIN, COMBINAISON) " +
                "VALUES ("+ id_partie +","+ num_manche +","+ id_joueur +","+round.getGain()+",'"+ round.getCombination().getName()+"');";
        Log.i("var", "REQ : "+req);
        content.execSQL(req);
    }
}
