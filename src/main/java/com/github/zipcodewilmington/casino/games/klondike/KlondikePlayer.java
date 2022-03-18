package com.github.zipcodewilmington.casino.games.klondike;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.WagingPlayer;

public class KlondikePlayer extends WagingPlayer {
    @Override
    public CasinoAccount getCasinoAccount() {
        return this.casinoAccount;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }


}
