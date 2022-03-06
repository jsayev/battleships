package com.guestline.pre_interview.battleships;

import java.util.Random;

/**
 * Tosses coin to decide which player starts the game first. Makes use of {@link Random} class.
 *
 * @author Jovhar Isayev
 * @see com.guestline.pre_interview.battleships.Computer
 * @see User
 */
public final class CoinTosser {
    public static final int POSSIBILITIES = 2;
    private final Player computer;
    private final Player user;

    /**
     * Initializes CoinTosser object with provided participants of the game.
     *
     * @param computer
     * @param user
     */
    public CoinTosser(Player computer, Player user) {
        this.computer = computer;
        this.user = user;
    }

    /**
     * Throws coin to decide
     *
     * @return
     */
    public Player toss() {
        int faceOfCoin = new Random().nextInt(POSSIBILITIES);
        return faceOfCoin == 0 ? computer : user;
    }
}
