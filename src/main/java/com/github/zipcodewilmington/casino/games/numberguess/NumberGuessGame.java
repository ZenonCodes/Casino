package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;
import com.github.zipcodewilmington.casino.WagingPlayer;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame extends WagingGame implements GameInterface<NumberGuessPlayer> {

    Scanner scanner = new Scanner(System.in); //Scanner Class
    WagingPlayer player = new NumberGuessPlayer();
    private final int num = 1 + (int) (10 * Math.random());// Computer Choice
    public static  String MSG_WINNER = "Jackpot!!!!!";
    public boolean isOver = false;




    public void numberGuessGame(){
        int guess;
        player.setBet();
        getWagers(player.getBet());


        System.out.println("Pick a number between 1 and 10");
        guess = scanner.nextInt();
        findWinner(guess, num);
        System.out.println(MSG_WINNER);
        System.out.println("Your new balance is " + player.getAccountBalance() + "\n");
        System.out.println("Play Again? Y or N ");
        String choice = scanner.next().toUpperCase(Locale.ROOT);
        if(choice.equals("Y")) {
            numberGuessGame();
        } else {
            isOver();
        }

    }

    public String findWinner(int guess, int num) {
        if (guess == num) {
            player.setAccountBalance(player.getAccountBalance() + getPot());
            player.getCasinoAccount().setAccountBalance(player.getAccountBalance());
            MSG_WINNER = "Jackpot!!!!!";
            return MSG_WINNER;
        } else if (guess > num) {
            player.setAccountBalance(player.getAccountBalance() - getPot());
            player.getCasinoAccount().setAccountBalance(player.getAccountBalance());
            MSG_WINNER = "Guess too high, the house wins!";
            return MSG_WINNER;
        } else {
            player.setAccountBalance(player.getAccountBalance() - getPot());
            player.getCasinoAccount().setAccountBalance(player.getAccountBalance());
            MSG_WINNER = "Guess too low, the house wins!";
            return MSG_WINNER;
        }

    }


    //Actual Game




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