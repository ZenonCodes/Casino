package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.casino.games.blackjack.BlackJackGame;
import com.github.zipcodewilmington.casino.games.blackjack.BlackJackPlayer;
import com.github.zipcodewilmington.casino.games.craps.CrapsGame;
import com.github.zipcodewilmington.casino.games.craps.CrapsPlayer;
import com.github.zipcodewilmington.casino.games.klondike.KlondikeGame;
import com.github.zipcodewilmington.casino.games.klondike.KlondikePlayer;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.casino.games.war.WarGame;
import com.github.zipcodewilmington.casino.games.war.WarPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

/**
 * Created by leon on 7/21/2020.
 */
public class Casino implements Runnable {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    CasinoAccountManager casinoAccountManager;
    public Casino (CasinoAccountManager casinoAccountManager){
        this.casinoAccountManager = casinoAccountManager;

    }

    @Override
    public void run() {
        String arcadeDashBoardInput;
        CasinoAccount casinoAccount;

        do {
            arcadeDashBoardInput = getArcadeDashboardInput();
            if ("select-game".equals(arcadeDashBoardInput)) {
                String accountName = console.getStringInput("Enter your account name:");
                String accountPassword = console.getStringInput("Enter your account password:");
                casinoAccount = casinoAccountManager.getAccount(accountName, accountPassword);
                boolean isValidLogin = casinoAccount != null;
                if (isValidLogin) {
                    String gameSelectionInput = getGameSelectionInput().toUpperCase();
                    if (gameSelectionInput.equals("SLOTS")) {
                        play(new SlotsGame(), new SlotsPlayer(), accountName);
                    } else if (gameSelectionInput.equals("NUMBERGUESS")) {
                        play(new NumberGuessGame(), new NumberGuessPlayer(), accountName);
                    } else if (gameSelectionInput.equals("BLACKJACK")) {
                        play(new BlackJackGame(), new BlackJackPlayer(), accountName);
                    } else if (gameSelectionInput.equals("KLONDIKE")) {
                        play(new KlondikeGame(), new KlondikePlayer(), accountName);
                    }else if (gameSelectionInput.equals("CRAPS")) {
                        play(new CrapsGame(), new CrapsPlayer(), accountName);
                    }else if (gameSelectionInput.equals("WAR")) {
                        play(new WarGame(), new WarPlayer(), accountName);
                    } else {
                        // TODO - implement better exception handling
                        String errorMessage = "[ %s ] is an invalid game selection";
                        throw new RuntimeException(String.format(errorMessage, gameSelectionInput));
                    }
                } else {
                    // TODO - implement better exception handling
                    String errorMessage = "No account found with name of [ %s ] and password of [ %s ]";
                    throw new RuntimeException(String.format(errorMessage, accountPassword, accountName));
                }
            } else if ("create-account".equals(arcadeDashBoardInput)) {
                console.println("Welcome to the account-creation screen.");
                String accountName = console.getStringInput("Enter your account name:");
                String accountPassword = console.getStringInput("Enter your account password:");
                CasinoAccount newAccount = casinoAccountManager.createAccount(accountName, accountPassword);
//                casinoAccountManager.registerAccount(newAccount);
            }
            else if ("check-balance".equals(arcadeDashBoardInput)) {
                console.println("To get your account balance...");
                String accountName = console.getStringInput("Enter your account name:");
                casinoAccount = casinoAccountManager.getAccountByUsername(accountName);
                System.out.println(casinoAccount.getAccountBalance());
            }
        } while (!"logout".equals(arcadeDashBoardInput));
    }

    private String getArcadeDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Arcade Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ create-account ], [ select-game ], [check-balance]")
                .toString());
    }

    private String getGameSelectionInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Game Selection Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ SLOTS ], [ NUMBERGUESS ], [ WAR ], [ KLONDIKE ], [ CRAPS ], [ BLACKJACK ] ")
                .toString());
    }

    private void play(GameInterface gameObject, Player playerObject, String accountName) {
        GameInterface game = gameObject;
        Player player = playerObject;
        player.assignCasinoAccount(casinoAccountManager.getAccountByUsername(accountName));
        game.addPlayer(player);
        game.run();
    }
}
