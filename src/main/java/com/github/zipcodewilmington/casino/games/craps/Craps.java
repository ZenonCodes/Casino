package com.github.zipcodewilmington.casino.games.craps;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;
import com.github.zipcodewilmington.casino.WagingPlayer;
import com.github.zipcodewilmington.casino.dice.Dice;


import java.util.List;
import java.util.Scanner;

public class Craps extends WagingGame implements GameInterface<CrapsPlayer> {
    static WagingPlayer player = new CrapsPlayer();

    public static void main(String[] args) {

        System.out.println("You have " + player.getAccountBalance() + " to bet.\n");
        player.setBet();
        getWagers(player.getBet());
        System.out.println("\nWelcome to the Craps Table!");
        play();
        playGame();
    }

    public static void playGame() {
        while (continuePlaying()) {

            int rollSum = Dice.getRollSum();

            getInitialRollDiceMessage();
            getUserInput();

            getRollSumMessage(rollSum);

            if (rollSum == 7 || rollSum == 11) {
                System.out.println("You won!!");

            }
            else if (rollSum == 2 || rollSum == 3 || rollSum == 12) {
                System.out.println("You lose!");

            }
            else {
                System.out.println("Your point number is " + rollSum + "\n");
                pointRoll(rollSum);

            }
        }
    }

    private static void pointRoll ( int point){
            int rollSum;
            while (true) {
                getRollDiceMessage();
                getUserInput();

                rollSum = Dice.getRollSum();
                getRollSumMessage(rollSum);

                if (rollSum == point) {
                    System.out.println("You've rolled your point number. You've won!\n");
                    break;
                } else if (rollSum == 7) {
                    System.out.println("Sorry, by rolling a 7 you lose!!\n");
                    break;
                }

            }
        }


        private static void getRules () {
            System.out.println("\nWould you like to learn how to play Craps? (Yes/No)");
            if (getUserInput().equals("Yes")) {
                gameObjectiveMessage();
            }
        }


        private static void gameObjectiveMessage () {
            System.out.println("\nHow to play Craps: "
                    + "\nYou be rolling a pair of dice each turn "
                    + "\nOn your first roll, if you roll a 2, 3, or 12, you lose!"
                    + "\nTo win on your first roll, you must roll a 7 or 11.\n"
                    + "\nIf you roll a 4, 5, 6, 8, 9, or 10, on your first roll, this is called your point number"
                    + "\nNow you must roll the dice repeatedly in hopes of matching the point number"
                    + "\nHowever if you roll a 7 before rolling your point number, you lose!");
        }


        private static void getRollSumMessage ( int rollSum){
            System.out.println("You've rolled " + rollSum);
        }

        private static void getInitialRollDiceMessage () {
            System.out.println("\nPress ENTER to Roll the dice");
        }

        private static void getRollDiceMessage () {
            System.out.println("\nRoll the dice");
        }


        private static void play () {
            System.out.println("\nWould you like to play Craps? (Yes/No)");
            if (getUserInput().equals("Yes")) {
                getRules();
            }
        }

        private static boolean continuePlaying () {
            System.out.println("\nReady To Roll? (Yes/No)");
            return getUserInput().equals("Yes");
        }

        public static String getUserInput () {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        }

    @Override
    public Boolean isOver() {
        return null;
    }

    @Override
    public void addPlayer(CrapsPlayer player) {

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

    }
}




