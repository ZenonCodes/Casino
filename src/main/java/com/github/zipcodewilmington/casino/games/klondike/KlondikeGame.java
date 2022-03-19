package com.github.zipcodewilmington.casino.games.klondike;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;
import com.github.zipcodewilmington.casino.WagingPlayer;
import com.github.zipcodewilmington.casino.dice.Dice;

import java.util.*;

public class KlondikeGame extends WagingGame implements GameInterface<KlondikePlayer> {
    Scanner scanner = new Scanner(System.in);
    WagingPlayer player = new KlondikePlayer();
    ArrayList<Integer> houseRoll = new ArrayList<>();
    ArrayList<Integer> playerRoll = new ArrayList<>();
    HashMap<String, Integer> houseHand = new HashMap<>();
    HashMap<String, Integer> playerHand = new HashMap<>();
    String klondikeHand = "";
    String playerklondikeHand = "";
    int houseHandRank;
    int playerHandRank;
    String WinMsg = "";

    public void klondikeGame () {
        System.out.println("You have " + player.getAccountBalance() + " to bet.\n");
        player.setBet();
        getWagers(player.getBet());
        System.out.println("Press any key to roll the dice...\n");
        String roll = scanner.next();
        if (roll != null){
            getPlayerRoll();
        }
        evaluatePlayerHand();
        getPlayerKlondikeHand();
        System.out.println("You have " + playerklondikeHand + " !\n");
        playerRoll.clear();
        getHouseRoll();
        evaluateHouseHand();
        getKlondikeHand();
        System.out.println("The house has " + klondikeHand + " !\n");
        houseRoll.clear();
        getWinner();
        System.out.println(WinMsg);
        System.out.println("\n");
        System.out.println("You now have " + player.getAccountBalance() + " to bet.\n");
        System.out.println("Play again? Y or N ? \n");
        String choice = scanner.next().toUpperCase();
        if (choice.equals("Y")) {
            klondikeGame();
        } else {
            isOver();
        }
    }

    public ArrayList<Integer> getHouseRoll() {
        houseRoll = Dice.roll(5);
        Collections.sort(houseRoll);
        return houseRoll;
    }

    public ArrayList<Integer> getPlayerRoll() {
        playerRoll = Dice.roll(5);
        Collections.sort(playerRoll);
        return playerRoll;
    }

    public HashMap<String, Integer> evaluateHouseHand() {
        houseHand.put("ones", Collections.frequency(houseRoll, 1));
        houseHand.put("twos", Collections.frequency(houseRoll, 2));
        houseHand.put("threes", Collections.frequency(houseRoll, 3));
        houseHand.put("fours", Collections.frequency(houseRoll, 4));
        houseHand.put("fives", Collections.frequency(houseRoll, 5));
        houseHand.put("sixes", Collections.frequency(houseRoll, 6));
        return houseHand;

    }

    public int getKlondikeHand() {

        if (houseHand.containsValue(5)) {
            klondikeHand = "Five of a Kind";
            houseHandRank = 6;
            return houseHandRank;
        } else if (houseHand.containsValue(4)) {
            klondikeHand = "Four of a Kind";
            houseHandRank = 5;
            return houseHandRank;
        } else if (houseHand.containsValue(3) && houseHand.containsValue(2)) {
            klondikeHand = " a Full House";
            houseHandRank = 4;
            return houseHandRank;
        } else if (houseHand.containsValue(3)) {
            klondikeHand = "Three of a Kind";
            houseHandRank = 3;
            return houseHandRank;
        } else if (houseHand.containsValue(2)) {
            if (isTwoPair(houseRoll)) {
                klondikeHand = "Two Pair";
                houseHandRank = 2;
            } else {
                klondikeHand = " a Pair";
                houseHandRank = 1;
                return houseHandRank;
            }
        } else {
            klondikeHand = "Tied, the house wins...";
            houseHandRank = 7;
        }
        return houseHandRank;
    }

    public HashMap<String, Integer> evaluatePlayerHand() {
        playerHand.put("ones", Collections.frequency(playerRoll, 1));
        playerHand.put("twos", Collections.frequency(playerRoll, 2));
        playerHand.put("threes", Collections.frequency(playerRoll, 3));
        playerHand.put("fours", Collections.frequency(playerRoll, 4));
        playerHand.put("fives", Collections.frequency(playerRoll, 5));
        playerHand.put("sixes", Collections.frequency(playerRoll, 6));
        return playerHand;
    }

    public int getPlayerKlondikeHand() {

        if (playerHand.containsValue(5)) {
            playerklondikeHand = "Five of a Kind";
            playerHandRank = 6;
            return playerHandRank;
        } else if (playerHand.containsValue(4)) {
            playerklondikeHand = "Four of a Kind";
            playerHandRank = 5;
            return playerHandRank;
        } else if (playerHand.containsValue(3) && houseHand.containsValue(2)) {
            playerklondikeHand = " a Full House";
            playerHandRank = 4;
            return playerHandRank;
        } else if (playerHand.containsValue(3)) {
            playerklondikeHand = "Three of a Kind";
            playerHandRank = 3;
            return playerHandRank;
        } else if (playerHand.containsValue(2)) {
            if (isTwoPair(playerRoll)) {
                playerklondikeHand = "Two Pair";
                playerHandRank = 2;
            } else {
                playerklondikeHand = " a Pair";
                playerHandRank = 1;
                return playerHandRank;
            }
        } else {
            playerklondikeHand = "Tied, the house wins...";
            playerHandRank = 0;
        }
        return playerHandRank;
    }

    public boolean isTwoPair(ArrayList<Integer> hand) {
        HashSet<Integer> newHash = new HashSet<>(hand);
        return newHash.size() == 3;
    }

    public String getWinner() {
        if (houseHandRank > playerHandRank) {
            player.setAccountBalance(player.getAccountBalance() - getPot());
            player.getCasinoAccount().setAccountBalance(player.getAccountBalance());
            WinMsg = "HOUSE WINS!!!!";
            return WinMsg;
        } else if (playerHandRank > houseHandRank) {
            player.setAccountBalance(player.getAccountBalance() + getPot());
            player.getCasinoAccount().setAccountBalance(player.getAccountBalance());
            WinMsg = "YOU WIN!!!";
            return WinMsg;
        } else {
            player.setAccountBalance(player.getAccountBalance() - getPot());
            player.getCasinoAccount().setAccountBalance(player.getAccountBalance());
            WinMsg = "TIE, SO THE HOUSE WINS!!!!";
            return WinMsg;
        }
    }



    @Override
    public Boolean isOver() {
        return true;
    }

    @Override
    public void addPlayer(KlondikePlayer player) {
        this.player = player;
    }

    @Override
    public void addPlayers(List<? extends KlondikePlayer> player) {

    }

    @Override
    public void evaluateTurn(KlondikePlayer player) {

    }

    @Override
    public void remove(KlondikePlayer player) {

    }

    @Override
    public void run() {
        klondikeGame();
    }
}
