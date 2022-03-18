package com.github.zipcodewilmington.casino.games.craps;

import com.github.zipcodewilmington.casino.dice.Dice;

import java.util.Scanner;

public class Craps {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to Craps!");

        playGame(scanner);
    }

    private static void playGame(Scanner scanner) {

        getRules(scanner);
        while (continuePlaying(scanner)) {

            int rollSum = Dice.getRollSum();

            getRollDiceMessage();
            scanner.nextLine();

            getRollSumMessage(rollSum);

            if (rollSum == 7 || rollSum == 11) {
                System.out.println("You won!!");

            } else if (rollSum == 2 || rollSum == 3 || rollSum == 12) {
                System.out.println("You lose!");

            } else {
                System.out.println("Your point number is " + rollSum + "\n");
                pointRoll(scanner, rollSum);

            }

        }
    }


    private static void pointRoll(Scanner scanner, int point) {
        int rollSum;
        while (true) {
            getRollDiceMessage();
            scanner.nextLine();

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


    private static boolean getRules(Scanner scanner) {
        System.out.println("\nWould you like to learn how to play Craps? (Yes/No)");
        String replay = scanner.nextLine();
        if (replay.equals("Yes")) {
            return gameObjectiveMessage();
        } else if (replay.equals("No")) {
            return false;
        }
        return false;
    }


    private static boolean gameObjectiveMessage() {
        System.out.println("\nHow to play Craps: "
                + "\nYou be rolling a pair of dice each turn "
                + "\nOn your first roll, if you roll a 2, 3, or 12, you lose!"
                + "\nTo win on your first roll, you must roll a 7 or 11.\n"
                + "\nIf you roll a 4, 5, 6, 8, 9, or 10, on your first roll, this is called your point number"
                + "\nNow you must roll the dice repeatedly in hopes of matching the point number"
                + "\nHowever if you roll a 7 before rolling your point number, you lose!");
        return false;
    }


    private static void getRollSumMessage(int rollSum) {
        System.out.println("You've rolled " + rollSum);
    }

    private static void getRollDiceMessage() {
        System.out.println("\nRoll the dice");
    }

    private static boolean continuePlaying(Scanner scanner) {
        System.out.println("\nWould you like to play Craps? (Yes/No)");
        String replay = scanner.nextLine();

        if (replay.equals("Yes")) {
            return true;
        } else if (replay.equals("No")) {
            return false;
        }
        return false;
    }

}







//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("\nWelcome to Craps!");
//
//        while (continuePlaying(scanner)) {
//
//            int roll1 = Dice.getDiceRoll();
//            int roll2 = Dice.getDiceRoll();
//            int rollSum = roll1 + roll2;
//
//            System.out.println("Press Enter to roll your dice!");
//            scanner.nextLine();
//
//            System.out.println("\nYou rolled a " + roll1 + " and a " + roll2 + " for a total of " + rollSum);
//
//            if (rollSum == 7 || rollSum == 11) {
//                System.out.println("You've won!!");
//
//
//            } else if (rollSum == 2 || rollSum == 3 || rollSum == 12) {
//                System.out.println("You lose!");
//
//
//            } else {
//                int point = rollSum;
//                System.out.println("Your point number is " + rollSum + "\n");
//
//                while (true) {
//                    System.out.println("Press enter to roll your dice again! ");
//                    scanner.nextLine();
//
//                    roll1 = Dice.getDiceRoll();
//                    roll2 = Dice.getDiceRoll();
//                    rollSum = roll1 + roll2;
//                    System.out.println("You've rolled a " + roll1 + " and a " + roll2 + " for a total of " + rollSum);
//
//                    if (rollSum == point) {
//                        System.out.println("You've rolled your point number. You've won!\n");
//                        break;
//                    } else if (rollSum == 7) {
//                        System.out.println("Sorry, by rolling a 7 you lose!!\n");
//                        break;
//                    }
//
//                }
//
//            }
//
//        }
//    }
//
//    private static boolean continuePlaying(Scanner scanner) {
//        System.out.println("\nWould you like to play Craps? (Yes/No)");
//        String replay = scanner.nextLine();
//
//        if (replay.equals("Yes")){
//            return true;
//        } else if (replay.equals("No")){
//            return false;
//        }
//        return false;
//    }




