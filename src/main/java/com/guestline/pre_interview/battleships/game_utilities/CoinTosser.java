package com.guestline.pre_interview.battleships.game_utilities;

import java.util.Random;

/**
 * Tosses coin to decide which participant goes first. Makes use of {@link Random} class.
 *
 * @author Jovhar Isayev
 */
public final class CoinTosser<T> {
    public static final int POSSIBILITIES = 2;
    private final T participantOne;
    private final T participantTwo;

    public CoinTosser(T participantOne, T participantTwo) {
        this.participantOne = participantOne;
        this.participantTwo = participantTwo;
    }

    /**
     * Throws coin to decide which participant goes first.
     *
     * @return {@link T}
     */
    public T toss() {
        int faceOfCoin = new Random().nextInt(POSSIBILITIES);
        return faceOfCoin == 0 ? participantOne : participantTwo;
    }
}
