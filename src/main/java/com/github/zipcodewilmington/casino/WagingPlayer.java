package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Scanner;

public abstract class WagingPlayer extends Player {
    int bet;
    Scanner scanner = new Scanner(System.in);


    public void setBet(){
        System.out.println("How much would you like to wager? \n");
        int bet = scanner.nextInt();
    }

    public int getBet(){
        return bet;
    }

}