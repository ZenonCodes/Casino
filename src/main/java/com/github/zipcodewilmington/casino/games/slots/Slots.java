package com.github.zipcodewilmington.casino.games.slots;

import java.util.Random;
import java.util.Scanner;

public class Slots {

    public static void main(String[] args) {
        Random generator = new Random();
        Scanner console = new Scanner(System.in);

        int input;
        int wager = 0;
        int iSlot1, iSlot2, iSlot3;
        do {
            System.out.println("Slot Machine");
            System.out.println("How many tokens would you like to wager");
            wager = console.nextInt();
            System.out.println("Press 1 to pull, 2 to quit");

            input = console.nextInt();

            iSlot1 = generator.nextInt(3)+1; //the bound number is setting the 3 range of numbers, the number after the + is where the number starts so ex) + 7 would give combos of 789
            iSlot2 = generator.nextInt(3)+1;
            iSlot3 = generator.nextInt(3)+1;

            System.out.println(iSlot1 + " " + iSlot2 + " " + iSlot3);

            if (iSlot1 == iSlot2 && iSlot1 == iSlot3) {
                System.out.println("You win 10 tokens");
                wager += 10;
            } else if (iSlot1 == iSlot2 || iSlot2 == iSlot3) {
                System.out.println("You lost 5 tokens");
                wager += 5;
            } else {
                System.out.println("You lost a token");
                wager -= 1;
            }
        } while (input == 1);
    }
}

