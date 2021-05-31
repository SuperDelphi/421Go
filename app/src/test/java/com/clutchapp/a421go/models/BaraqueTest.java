package com.clutchapp.a421go.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BaraqueTest {
    private ArrayList<Dice> dices = new ArrayList<>();
    private Baraque baraque;

    public BaraqueTest() {
        dices.add(new Dice(2));
        dices.add(new Dice(2));
        dices.add(new Dice(2));

        baraque = new Baraque(dices);
    }

    @Test
    public void testGetPoints() {
        assertEquals(2, baraque.getPoints());
    }
}