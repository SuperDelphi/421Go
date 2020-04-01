package com.example.a421go.models;

import java.util.ArrayList;

public class Suite extends Combination {

    public Suite(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Suite");
        super.setPoints(2);
    }
}
