package com.example.a421go.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.a421go.metier.SQLiteManager;

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
}
