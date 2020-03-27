package com.example.a421go.metier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteManager extends SQLiteOpenHelper {

    /**
     * Script SQL permettant la création des tables.
     */
    private String creationSQL =
            "SET SQL_MODE = \"NO_AUTO_VALUE_ON_ZERO\";\n" +
            "SET AUTOCOMMIT = 0;\n" +
            "START TRANSACTION;\n" +
            "SET time_zone = \"+00:00\";\n" +
            "\n" +
            "/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;\n" +
            "/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;\n" +
            "/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;\n" +
            "/*!40101 SET NAMES utf8mb4 */;\n" +
            "\n" +
            "DROP TABLE IF EXISTS `joueur`;\n" +
            "CREATE TABLE IF NOT EXISTS `joueur` (\n" +
            "  `id_joueur` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `nom` varchar(45) NOT NULL,\n" +
            "  `nb_victoire` int(11) NOT NULL DEFAULT '0',\n" +
            "  PRIMARY KEY (`id_joueur`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
            "\n" +
            "DROP TABLE IF EXISTS `partie`;\n" +
            "CREATE TABLE IF NOT EXISTS `partie` (\n" +
            "  `id_partie` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `date_creation` date NOT NULL,\n" +
            "  `target_score` int(11) NOT NULL,\n" +
            "  PRIMARY KEY (`id_partie`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
            "\n" +
            "DROP TABLE IF EXISTS `tour`;\n" +
            "CREATE TABLE IF NOT EXISTS `tour` (\n" +
            "  `id_partie` int(11) NOT NULL,\n" +
            "  `num_manche` int(11) NOT NULL,\n" +
            "  `id_joueur` int(11) NOT NULL,\n" +
            "  `gain` int(11) NOT NULL,\n" +
            "  `combinaison` varchar(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id_partie`,`num_manche`,`id_joueur`),\n" +
            "  KEY `id_joueur_idx` (`id_joueur`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +
            "\n" +
            "ALTER TABLE `tour`\n" +
            "  ADD CONSTRAINT `id_joueur_fk` FOREIGN KEY (`id_joueur`) REFERENCES `joueur` (`id_joueur`),\n" +
            "  ADD CONSTRAINT `id_partie_fk` FOREIGN KEY (`id_partie`) REFERENCES `partie` (`id_partie`);\n" +
            "COMMIT;\n" +
            "\n" +
            "/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;\n" +
            "/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;\n" +
            "/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;";

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
        db.execSQL(creationSQL);
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
