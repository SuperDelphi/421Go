package com.example.a421go.metier;

import com.example.a421go.models.Round;

import java.util.Comparator;

public class RoundComparator implements Comparator<Round> {

    public RoundComparator(){}

    @Override
    public int compare(Round round1, Round round2){
        if (round1.getGain() == round2.getGain()) return 0;
        else if (round1.getGain() > round2.getGain()) return 1;
        else return -1;
    }
}
