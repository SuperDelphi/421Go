package com.example.a421go.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.a421go.metier.SQLiteManager;

/**
 * Représente la base de données du jeu.
 */
public class GameDatabase {
    private String name = "gameDB.sqlite";
    private int version = 1;
    private SQLiteManager manager;
    private SQLiteDatabase content;
    private static GameDatabase instance = null;

    /**
     * Le constructeur de la classe.
     * @param context le contexte.
     */
    protected GameDatabase(Context context) {
        manager = new SQLiteManager(context, name, null, version);
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     * @return L'unique instance de la classe.
     */
    public static GameDatabase getInstance(Context context) {
        if (GameDatabase.instance == null) {
            GameDatabase.instance = new GameDatabase(context);
        }
        GameDatabase db = new GameDatabase(context);
        return instance;
    }

//    public void addPlayer(Player player) {
//        content = manager.getWritableDatabase();
//        String req = "INSERT INTO "
//    }
}
