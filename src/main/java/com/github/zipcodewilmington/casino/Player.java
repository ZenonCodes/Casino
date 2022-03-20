package com.github.zipcodewilmington.casino;



public abstract class Player implements PlayerInterface {
    public CasinoAccount casinoAccount;
    Boolean isTurn;

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }

    public void assignCasinoAccount(CasinoAccount casinoAccount) {
        this.casinoAccount = casinoAccount;
    }

    @Override
    public CasinoAccount getCasinoAccount() {
        return this.casinoAccount;
    }

}