package com.clutchapp.a421go.models;

import android.util.Log;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player p1 = new Player("P1");
    Player p2 = new Player("P2");

    @Test
    void getVictories() {
        assertEquals(0, p1.getVictories(), "Le nombre de victoires doit être nul.");
    }

    @Test
    void addVictory() {
        p1.addVictory();
        assertEquals(1, p1.getVictories(), "Le nombre de victoires doit être égal à 1.");
    }

    @Test
    void compareTo() {
        p1.setScoreFinal(0);
        p2.setScoreFinal(0);
        assertEquals(0, p1.compareTo(p2));

        p1.setScoreFinal(1);
        assertEquals(-1, p1.compareTo(p2));
        assertEquals(1, p2.compareTo(p1));
    }
}