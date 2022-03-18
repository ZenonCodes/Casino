package com.github.zipcodewilmington.casino.games.klondike;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.Player;

public class KlondikePlayer extends Player {
    @Override
    public CasinoAccount getCasinoAccount() {
        return this.casinoAccount;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }

    @Override
    public void run() {

    }
}
