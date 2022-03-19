package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.CasinoAccountManager;

public class MainApplication {
    public static void main(String[] args) {
        CasinoAccountManager casinoAccountManager = new CasinoAccountManager();
        Casino casino = new Casino(casinoAccountManager);

        casino.run();
    }
}
