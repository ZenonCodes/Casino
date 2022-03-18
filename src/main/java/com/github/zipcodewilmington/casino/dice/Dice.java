package com.github.zipcodewilmington.casino.dice;

import java.util.ArrayList;


public class Dice {
    static ArrayList<Integer> roll = new ArrayList<>();
    public static int numDice;

    public Dice (int numDice) {
        Dice.numDice = numDice;
    }

    public static ArrayList<Integer> roll(int numDice){
     for (int i = 0; i < numDice; i++) {
        roll.add(i, (int) Math.floor(Math.random() * 6 + 1));
    }
        System.out.println(roll);
        return roll;
    }

    public static int getDiceRoll() {
        return (int)(Math.random() * 6) + 1;
    }

    public static int getRollSum() {
        int roll1 = getDiceRoll();
        int roll2 = getDiceRoll();
        return roll1 + roll2;
    }


    @Override
    public String toString() {
        return "Dice{}";
    }
}
