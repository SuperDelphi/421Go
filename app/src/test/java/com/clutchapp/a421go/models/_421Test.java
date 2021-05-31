package com.clutchapp.a421go.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class _421Test {
    private ArrayList<Dice> dices = new ArrayList<>();
    private _421 comb421;

    public _421Test() {
        dices.add(new Dice(1));
        dices.add(new Dice(2));
        dices.add(new Dice(4));

        comb421 = new _421(dices);
    }

    @Test
    public void testGetPoints() {
        assertEquals(10, comb421.getPoints());
    }
}