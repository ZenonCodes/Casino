package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.GameInterface;

import java.util.List;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame implements GameInterface<NumberGuessPlayer> {

    Scanner scanner = new Scanner(System.in); //Scanner Class
    int num = 1 + (int) (10 * Math.random());// Computer Choice
    boolean isOver = false;


    //Actual Game
    public void numberGuessgame(){
        do {
            System.out.println("Pick a number between 1 and 10");
            int guess = scanner.nextInt();
            if (guess == num) {
                System.out.println("Jackpot!!!!!!!");
                isOver();
            } else if (guess > num) {
                System.out.println("Guess is too high, you lose, the house wins...");
               isOver();
            } else {
                System.out.println("Guess is too low, you lose, the house wins...");
                isOver();
            }
            scanner.close();
    } while(isOver);

    }



    @Override
    public Boolean isOver() {
        isOver = true;
        return true;
    }

    @Override
    public void addPlayer(NumberGuessPlayer player) {

    }

    @Override
    public void addPlayers(List<? extends NumberGuessPlayer> player) {

    }

    @Override
    public void evaluateTurn(NumberGuessPlayer player) {

    }

    @Override
    public void remove(NumberGuessPlayer player) {

    }

    @Override
    public void run() {

    }
}