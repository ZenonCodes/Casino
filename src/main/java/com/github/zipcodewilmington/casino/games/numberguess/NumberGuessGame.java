package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;
import com.github.zipcodewilmington.casino.WagingPlayer;

import java.util.List;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame extends WagingGame implements GameInterface<NumberGuessPlayer> {

    Scanner scanner = new Scanner(System.in); //Scanner Class
    WagingPlayer player = new NumberGuessPlayer();
    private final int num = 1 + (int) (10 * Math.random());// Computer Choice
    public static final String MSG_WINNER = "Jackpot!!!!!";
    public static final String MSG_TOO_HIGH = "Guess too high, the house wins!";
    public static final String MSG_TOO_LOW = "Guess too low, the house wins!";
    public boolean isOver = false;


    public String findWinner(int guess, int num) {
        if (guess == num) {
            player.setAccountBalance(player.getAccountBalance() + getPot());
            player.getCasinoAccount().setAccountBalance(player.getAccountBalance());
            isOver();
            return MSG_WINNER;

        } else if (guess > num) {
            player.setAccountBalance(player.getAccountBalance() - getPot());
            player.getCasinoAccount().setAccountBalance(player.getAccountBalance());
            isOver();
            return MSG_TOO_HIGH;

        } else {
            player.setAccountBalance(player.getAccountBalance() - getPot());
            player.getCasinoAccount().setAccountBalance(player.getAccountBalance());
            isOver();
            return MSG_TOO_LOW;
        }
    }


    //Actual Game
    public void numberGuessGame(){
        int guess;
        player.setBet();
        getWagers(player.getBet());

        do {
            System.out.println("Pick a number between 1 and 10");
            guess = scanner.nextInt();
            findWinner(guess, num);

        } while(!isOver);


    }



    @Override
    public Boolean isOver() {
        isOver = true;
        return true;
    }

    @Override
    public void addPlayer(NumberGuessPlayer player) {
        this.player = player;

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
        numberGuessGame();
    }
}