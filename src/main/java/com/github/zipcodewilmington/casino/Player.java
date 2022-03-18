package com.github.zipcodewilmington.casino;



public abstract class Player implements PlayerInterface {
    public CasinoAccount casinoAccount;
    int accountBalance = casinoAccount.getAccountBalance();
    Boolean isTurn;

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }

    public abstract void run();
}