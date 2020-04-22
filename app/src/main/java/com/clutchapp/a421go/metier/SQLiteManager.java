package com.clutchapp.a421go.metier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Gère les échanges avec la base de données SQLite.
 */
public class SQLiteManager extends SQLiteOpenHelper {

    /**
     * Script SQL permettant la création des tables.
     */
    private String joueurSQL =  "CREATE TABLE IF NOT EXISTS JOUEUR (\n" +
            "  ID_JOUEUR INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "  NOM VARCHAR(45) NOT NULL,\n" +
            "  NB_VICTOIRE INTEGER NOT NULL DEFAULT 0\n" +
            ");\n" +
            "\n" ;
    private String partieSQL = "CREATE TABLE IF NOT EXISTS PARTIE (\n" +
                    "  ID_PARTIE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                    "  DATE_CREATION DATE NOT NULL,\n" +
                    "  TARGET_SCORE INTEGER NOT NULL\n" +
                    ");\n" +
                    "\n" ;
    private String tourSQL = "CREATE TABLE IF NOT EXISTS TOUR (\n" +
                    "  ID_PARTIE INTEGER NOT NULL,\n" +
                    "  NUM_MANCHE INTEGER NOT NULL,\n" +
                    "  ID_JOUEUR INTEGER NOT NULL,\n" +
                    "  GAIN INTEGER NOT NULL,\n" +
                    "  COMBINAISON VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY(ID_PARTIE, NUM_MANCHE, ID_JOUEUR),\n" +
                    "  FOREIGN KEY(ID_PARTIE) REFERENCES PARTIE(ID_PARTIE),\n" +
                    "  FOREIGN KEY(ID_JOUEUR) REFERENCES JOUEUR(ID_JOUEUR)\n" +
                    ");";

    /**
     * Le constructeur de la classe.
     * @param context le contexte.
     * @param name le nom.
     * @param factory la fabrique.
     * @param version le numéro de version.
     */
    public SQLiteManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Méthode appelée quand la base de données est créée pour la première fois.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(joueurSQL);
        db.execSQL(partieSQL);
        db.execSQL(tourSQL);
    }

    /**
     * Méthode appelée quand la base de donnée a besoin d'être mise à niveau.
     * @param db la base de données.
     * @param oldVersion l'ancien numéro de version.
     * @param newVersion le nouveau numéro de version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
