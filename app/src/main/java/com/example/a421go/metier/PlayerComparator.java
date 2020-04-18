package com.example.a421go.metier;

import com.example.a421go.models.Player;

import java.util.Comparator;

/**
 * Comparateur de joueurs ({@link Player}.
 */
public class PlayerComparator implements Comparator<Player> {
    /**
     * Constructeur de la classe.
     */
    public PlayerComparator() {}

    /**
     * Compare deux joueurs entre eux par rapport à leur score final.
     * @param player1 le premier joueur ({@link Player}).
     * @param player2 le second joueur ({@link Player}).
     * @return 1 si le premier joueur < le second joueur, 0 si le premier joueur = le second joueur,
     * -1 si le premier joueur > le second joueur.
     */
    @Override
    public int compare(Player player1, Player player2) {
        if (player1.getScoreFinal() == player2.getScoreFinal()) return 0;
        else if (player1.getScoreFinal() < player2.getScoreFinal()) return 1;
        else return -1;
    }
}
