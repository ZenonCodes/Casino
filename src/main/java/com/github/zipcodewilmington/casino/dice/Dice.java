package com.github.zipcodewilmington.casino.dice;

import java.util.ArrayList;


public class Dice {
    static ArrayList<Integer> roll = new ArrayList<>();
    public static int numDice;

    public Dice (int numDice) {
        Dice.numDice = numDice;
    }

    public static ArrayList<Integer> roll(){
     for (int i = 0; i < numDice; i++) {
        roll.set(i, (int) Math.floor(Math.random() * 6 + 1));
    }
        System.out.println(roll);
        return roll;
    }


}