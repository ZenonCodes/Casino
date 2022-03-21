package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;
import com.github.zipcodewilmington.casino.WagingPlayer;
import com.github.zipcodewilmington.casino.cards.Cards;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame extends WagingGame implements GameInterface<SlotsPlayer> {

    @Override
    public Boolean isOver() {
        return null;
    }

    @Override
    public void addPlayer(SlotsPlayer player) {

    }

    @Override
    public void addPlayers(List<? extends SlotsPlayer> player) {

    }

    @Override
    public void evaluateTurn(SlotsPlayer player) {

    }

    @Override
    public void remove(SlotsPlayer player) {

    }

    public static int getUserInput () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    @Override
    public void run() {
        Random generator = new Random();
//        Scanner console = new Scanner(System.in);

        int input;
        int wager = 0;
        int slot1, slot2, slot3;
        do {
            System.out.println("Slot Machine");
            System.out.println("How many tokens would you like to wager");
            wager = getUserInput();
            System.out.println("Press 1 to pull, 2 to quit");

            input = getUserInput();

            slot1 = generator.nextInt(6)+1; //the bound number is setting the 3 range of numbers, the number after the + is where the number starts so ex) + 7 would give combos of 789
            slot2 = generator.nextInt(6)+1;
            slot3 = generator.nextInt(6)+1;

            System.out.println(slot1 + " " + slot2 + " " + slot3);

            if (slot1 == slot2 && slot1 == slot3) {
                System.out.println("You win 10 tokens");
                wager += 10;
            } else if (slot1 == slot2 || slot2 == slot3) {
                System.out.println("You lost 5 tokens");
                wager += 5;
            } else {
                System.out.println("You lost a token");
                wager -= 1;
            }
        } while (input == 1);
    }
}
