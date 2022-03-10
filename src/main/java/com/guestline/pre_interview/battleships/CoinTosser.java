package com.guestline.pre_interview.battleships;

import java.util.Random;

/**
 * Tosses coin to decide which player starts the game first. Makes use of {@link Random} class.
 *
 * @author Jovhar Isayev
 */
public final class CoinTosser {
    public static final int POSSIBILITIES = 2;

    /**
     * Throws coin to decide which player starts the game first.
     *
     * @return {@link Gamer}
     */
    public static GameParticipant toss(GameParticipant[] gameParticipants) {
        int faceOfCoin = new Random().nextInt(POSSIBILITIES);
        return gameParticipants[faceOfCoin];
    }
}
