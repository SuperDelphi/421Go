package com.example.a421go.metier;

import com.example.a421go.models.Player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player>{

    public PlayerComparator(){}

    @Override
    public int compare(Player player1, Player player2){
        if (player1.getScoreFinal() == player2.getScoreFinal()) return 0;
        else if (player1.getScoreFinal() < player2.getScoreFinal()) return 1;
        else return -1;
    }
}
