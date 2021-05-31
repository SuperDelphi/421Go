package com.clutchapp.a421go.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    private Player p1 = new Player("P1");
    private Round round = new Round(p1, 3);

    @Test
    void setGain() {
        round.setGain(50);
        assertEquals(50, round.getGain(), "Le gain doit être égal à 50.");
    }

    @Test
    void addGain() {
        round.setGain(20);
        round.addGain(10);
        assertEquals(20 + 10, round.getGain(), "Le gain doit être égal à 30.");
    }
}