package com.example.a421go.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    public void addRound(Round round){
        //Paramêtre globale de la méthode
        content = manager.getWritableDatabase();
        String req;
        //Récupération de l'id de la dernière partie
        req = "SELECT PARTIE.ID_PARTIE FROM PARTIE";
        Cursor gameCursor = content.rawQuery(req, null);
        gameCursor.moveToLast();
        int id_partie = gameCursor.getType(gameCursor.getColumnIndex("ID_PARTIE"));
        //Récupération du numéro de la manche
        //Récupération de l'id du joueur
        //Requête qui ajout le tour
        req = "INSERT INTO TOUR (ID_PARTIE, NUM_MANCHE, ID_JOUEUR, GAIN, COMBINAISON) " +
                "VALUES ("+ id_partie +","+ 1 +","+ 1 +","+round.getGain()+","+ round.getCombination().getName()+");";
        content.execSQL(req);
    }
}
