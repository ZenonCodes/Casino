package com.github.zipcodewilmington.casino;

import java.util.Scanner;

public abstract class WagingPlayer extends Player {
    Scanner scanner = new Scanner(System.in);
    int accountBalance = 50000;
    int bet;

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setBet(){
        do {
            System.out.println("How much would you like to wager? \n");
            bet = scanner.nextInt();
        } while (bet > accountBalance || bet < 0);
    }

    public int getBet(){
        return bet;
    }

}