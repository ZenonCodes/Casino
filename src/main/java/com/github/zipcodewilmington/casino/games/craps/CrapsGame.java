package com.github.zipcodewilmington.casino.games.craps;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;
import com.github.zipcodewilmington.casino.dice.Dice;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.List;
import java.util.Scanner;

public class CrapsGame extends WagingGame implements GameInterface<CrapsPlayer> {
    final IOConsole console = new IOConsole(AnsiColor.AUTO);
    CrapsPlayer player;
    int playerBet;
    int winnings;
    int newPlayerBalance;

    @Override
    public Boolean isOver() {
        return null;
    }

    @Override
    public void addPlayer(CrapsPlayer player) {
        this.player = player;
    }

    @Override
    public void addPlayers(List<? extends CrapsPlayer> player) {

    }

    @Override
    public void evaluateTurn(CrapsPlayer player) {

    }

    @Override
    public void remove(CrapsPlayer player) {

    }

    @Override
    public void run() {
        final IOConsole console = new IOConsole(AnsiColor.CYAN);
        console.println("\nWelcome to the Craps Table!");
        console.println("You have " + player.getAccountBalance() + " to bet.\n");
        play();
        playGame();
    }

    public void playGame() {

        int playerBalance = player.getAccountBalance();

        while (continuePlaying()) {
            player.setBet();
            playerBet = player.getBet();

            int rollSum = Dice.getRollSum();

            getInitialRollDiceMessage();
            getUserInput();

            getRollSumMessage(rollSum);

            if (rollSum == 7 || rollSum == 11) {
                final IOConsole console = new IOConsole(AnsiColor.YELLOW);
                winnings = (playerBet * 3);
                console.println("You win " + winnings);
                newPlayerBalance = playerBalance + winnings;
                player.getCasinoAccount().setAccountBalance(newPlayerBalance);

            }
            else if (rollSum == 2 || rollSum == 3 || rollSum == 12) {
                final IOConsole console = new IOConsole(AnsiColor.RED);
                console.println("You lose! " + playerBet);
                newPlayerBalance = playerBalance - playerBet;
                player.getCasinoAccount().setAccountBalance(newPlayerBalance);


            }
            else {
                console.println("Your point number is " + rollSum + "\n");
                pointRoll(rollSum);

            }
        }
    }

    private void pointRoll ( int point){

        int rollSum;
        int playerBalance = player.getAccountBalance();

        while (true) {
            getRollDiceMessage();
            getUserInput();

            rollSum = Dice.getRollSum();
            getRollSumMessage(rollSum);

            if (rollSum == point) {
                final IOConsole console = new IOConsole(AnsiColor.YELLOW);
                winnings = (int) (playerBet * 1.5);
                console.println("You win " + winnings + "\n");
                newPlayerBalance = playerBalance + winnings;
                player.getCasinoAccount().setAccountBalance(newPlayerBalance);
                break;
            } else if (rollSum == 7) {
                final IOConsole console = new IOConsole(AnsiColor.RED);
                console.println("You lose! " + playerBet);
                newPlayerBalance = playerBalance - playerBet;
                player.getCasinoAccount().setAccountBalance(newPlayerBalance);
                break;
            }

        }
    }


    private static void getRules () {
        final IOConsole console = new IOConsole(AnsiColor.CYAN);
        console.println("\nWould you like to learn how to play Craps? (Yes/No)");
        if (getUserInput().equals("Yes")) {
            gameObjectiveMessage();
        }
    }


    private static void gameObjectiveMessage () {
        final IOConsole console = new IOConsole(AnsiColor.PURPLE);
        console.println("\nHow to play Craps: "
                + "\nYou be rolling a pair of dice each turn "
                + "\nOn your first roll, if you roll a 2, 3, or 12, you lose!"
                + "\nTo win on your first roll, you must roll a 7 or 11.\n"
                + "\nIf you roll a 4, 5, 6, 8, 9, or 10, on your first roll, this is called your point number"
                + "\nNow you must roll the dice repeatedly in hopes of matching the point number"
                + "\nHowever if you roll a 7 before rolling your point number, you lose!\n");
    }


    private void getRollSumMessage(int rollSum){
        final IOConsole console = new IOConsole(AnsiColor.CYAN);
        console.println("You've rolled " + rollSum);
    }

    private void getInitialRollDiceMessage () {
        console.println("\nPress ENTER to Roll the dice");
    }

    private void getRollDiceMessage () {
        console.println("\nRoll the dice");
    }


    private static void play () {
        final IOConsole console = new IOConsole(AnsiColor.CYAN);
        console.println("\nWould you like to play Craps? (Yes/No)");
        if (getUserInput().equals("Yes")) {
            getRules();
        }
    }

    private boolean continuePlaying () {
        console.println("\nReady To Roll? (Type 'Yes' to play / 'No' to quit)");
        return getUserInput().equals("Yes");
    }

    public static String getUserInput () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
