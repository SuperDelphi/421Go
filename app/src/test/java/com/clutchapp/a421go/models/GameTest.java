package com.clutchapp.a421go.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    Player p1 = new Player("J1");
    Player p2 = new Player("J2");
    ArrayList<Player> players = new ArrayList<>();

    public GameTest() {
        players.add(p1);
        players.add(p2);
        game = new Game(new Date(), 25, players);
    }

    @Test
    void testGetPlayersList() {
        assertEquals(players, game.getPlayersList());
    }

    @Test
    void testGetCurrentPlayer() {
        assertEquals(p1, game.getCurrentPlayer(), "Le joueur actuel doit être le premier joueur en début de partie.");
    }

    @Test
    void testGetCurrentRound() {
        assertEquals(true, game.getCurrentRound().getPlayer().equals(p1), "Le tour actuel doit appartenir au premier joueur en début de partie.");
    }

    @Test
    void testNextPlayer() {
        game.nextPlayer();
        assertEquals(p2, game.getCurrentPlayer(), "Le joueur suivant doit être le deuxième joueur.");
    }
}