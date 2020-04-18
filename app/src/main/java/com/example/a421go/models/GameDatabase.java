package com.example.a421go.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a421go.metier.SQLiteManager;

import java.util.ArrayList;
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
        cursor.close();
    }

    /**
     * Ajout un jeu
     * @param game
     */
    public void addGame(Game game){
        //Paramêtre globale de la méthode
        content = manager.getWritableDatabase();
        String req = "INSERT INTO PARTIE (DATE_CREATION, TARGET_SCORE) VALUES ('"+ new Date() +"', "+game.getTargetScore()+")";
        //Exécution
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
        req = "SELECT ID_JOUEUR FROM JOUEUR WHERE NOM = '"+ round.getPlayer().getName()+"';";
        Cursor playerCursor = content.rawQuery(req, null);
        playerCursor.moveToNext();
        int id_joueur = playerCursor.getInt(playerCursor.getColumnIndex("ID_JOUEUR"));
        //Requête qui ajout le tour
        req = "INSERT INTO TOUR (ID_PARTIE, NUM_MANCHE, ID_JOUEUR, GAIN, COMBINAISON) " +
                "VALUES ("+ id_partie +","+ num_manche +","+ id_joueur +","+round.getGain()+",'"+ round.getCombination().getName()+"');";
        content.execSQL(req);
        gameCursor.close();
        playerCursor.close();
    }

    /**
     * Récupére et envoie la liste des joueurs stockés dans la base de données
     * @return
     */
    public ArrayList<Player> getPlayers() {
        //Paramêtre globale de la méthode
        content = manager.getWritableDatabase();
        String req = "SELECT * FROM JOUEUR ORDER BY NB_VICTOIRE DESC";
        ArrayList<Player> playersList = new ArrayList<Player>();
        //Récupération de la liste des jouers
        Cursor playersCursor = content.rawQuery(req, null);
        //Création de la liste des joueurs
        while(playersCursor.moveToNext()){
            Player p = new Player(playersCursor.getString(playersCursor.getColumnIndex("NOM")));
            p.setVictories(playersCursor.getInt(playersCursor.getColumnIndex("NB_VICTOIRE")));
            playersList.add(p);
        }
        playersCursor.close();
        return playersList;
    }

    /**
     * Permet de renvoyer un objet Player en mettant à jour son nombre de victoire avec celui de la
     * base de données en fonction du joueur passé en paramètre
     * @param p un objet Player
     * @return un objet Player
     */
    public Player getVictoryPlayer(Player p){
        //Paramêtre globale de la méthode
        content = manager.getWritableDatabase();
        String req = "SELECT NB_VICTOIRE FROM JOUEUR WHERE NOM = '"+ p .getName()+"';";
        //Récupération du joueur depuis la BDD
        Cursor playerCursor = content.rawQuery(req, null);
        //Récupération des victoires du joueur
        playerCursor.moveToNext();
        p.setVictories(playerCursor.getInt(playerCursor.getColumnIndex("NB_VICTOIRE")));
        return p;
    }

    /**
     * Permet de mettre à jour le nombre de victoire stocké dans la base de données en fonction du
     * joueur passé en paramètre
     * @param p un objet Player
     */
    public void setVictoryPlayer(Player p){
        //Paramêtre globale de la méthode
        content = manager.getWritableDatabase();
        String req = "UPDATE JOUEUR SET NB_VICTOIRE = "+ p .getVictories() +" WHERE NOM = '"+ p .getName()+"';";
        //Exécution de la requête
        content.execSQL(req);
    }

}
