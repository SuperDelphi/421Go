package com.example.a421go.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.a421go.metier.SQLiteManager;

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

    public void addPlayer(Player player) {
        content = manager.getWritableDatabase();
        Log.i("var", "addPlayer: " + content.toString());
        String req = "INSERT INTO JOUEUR (NOM) VALUES ('J-C')";
        content.execSQL(req);
        Log.i("var", "insert : " + content.toString());
        String req2 = "SELECT * FROM JOUEUR WHERE 1";
        Cursor cursor = content.rawQuery(req2, new String[]{});
        cursor.moveToFirst();
        Log.i("var", "addPlayer: " + cursor.getString(1));
    }

    public void sertARien(){}
}
