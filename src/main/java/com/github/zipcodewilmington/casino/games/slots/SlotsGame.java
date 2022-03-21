package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;
import com.github.zipcodewilmington.casino.WagingPlayer;
import com.github.zipcodewilmington.casino.cards.Cards;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame extends WagingGame implements GameInterface<SlotsPlayer> {
    final IOConsole console = new IOConsole(AnsiColor.AUTO);
    SlotsPlayer player;
    int playerBet;
    int winnings;
    int newPlayerBalance;


    @Override
    public Boolean isOver() {
        return null;
    }

    @Override
    public void addPlayer(SlotsPlayer player) {
        this.player = player;
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
        int playerBalance = player.getAccountBalance();
        int slot1, slot2, slot3;

        welcomeMessage();

        console.println("\n\nYou have " + player.getAccountBalance() + " to bet.\n");

        do {

            player.setBet();
            playerBet = player.getBet();

            console.println("\nPress 1 to pull, 2 to quit");
            getUserInput();

            slot1 = generator.nextInt(6)+1; //the bound number is setting the 3 range of numbers, the number after the + is where the number starts so ex) + 7 would give combos of 789
            slot2 = generator.nextInt(6)+1;
            slot3 = generator.nextInt(6)+1;

            slotWheel(slot1, slot2, slot3);

            if (slot1 == slot2 && slot1 == slot3) {
                winnings = (playerBet * 2);
                youWinMessage();
                newPlayerBalance = playerBalance + winnings;
                player.getCasinoAccount().setAccountBalance(newPlayerBalance);

            } else if (slot1 == slot2 || slot2 == slot3) {
                winnings = (int) (playerBet * 1.25);
                youWinMessage();
                newPlayerBalance = playerBalance + winnings;
                player.getCasinoAccount().setAccountBalance(newPlayerBalance);

            } else {
                youLoseMessage();
                newPlayerBalance = playerBalance - playerBet;
                player.getCasinoAccount().setAccountBalance(newPlayerBalance);

            }

            console.println("\nPlay again? Press 1, Press 2 to quit");

        } while (getUserInput() == 1);
    }

    private void youLoseMessage() {
        final IOConsole console = new IOConsole(AnsiColor.RED);
        console.println("You lost " + playerBet);
    }

    private void youWinMessage() {
        final IOConsole console = new IOConsole(AnsiColor.YELLOW);
        console.println("You win " + winnings + "\n");
    }

    private void slotWheel(int slot1, int slot2, int slot3) {
        final IOConsole console = new IOConsole(AnsiColor.PURPLE);
        console.println("\n" + slot1 + "   " + slot2 + "   " + slot3 + "\n");
    }

    private void welcomeMessage() {
        final IOConsole console = new IOConsole(AnsiColor.CYAN);
        console.println("\nWelcome to the Slot Machine!!!" +
                "\n\nPull the lever to try your luck," +
                "\n3 in a row and you double your bet!!" +
                "\n2 matching and you get a little for your effort," +
                "\nif you have no matching reels you LOSE!\n");
    }
}
