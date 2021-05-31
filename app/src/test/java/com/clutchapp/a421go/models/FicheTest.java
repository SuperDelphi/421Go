package com.clutchapp.a421go.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FicheTest {
    private ArrayList<Dice> dices = new ArrayList<>();
    private Fiche fiche;

    public FicheTest() {
        dices.add(new Dice(1));
        dices.add(new Dice(1));
        dices.add(new Dice(4));

        fiche = new Fiche(dices);
    }

    @Test
    public void testGetPoints() {
        assertEquals(4, fiche.getPoints());
    }
}