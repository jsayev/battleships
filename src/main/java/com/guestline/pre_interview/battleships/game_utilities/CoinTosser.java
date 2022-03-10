package com.guestline.pre_interview.battleships.game_utilities;

import com.guestline.pre_interview.battleships.players.GameParticipant;

import java.util.Random;

/**
 * Tosses coin to decide which player starts the game first. Makes use of {@link Random} class.
 *
 * @author Jovhar Isayev
 */
public final class CoinTosser {
    public static final int POSSIBILITIES = 2;
    private final GameParticipant computer;
    private final GameParticipant user;

    public CoinTosser(GameParticipant computer, GameParticipant user) {
        this.computer = computer;
        this.user = user;
    }

    /**
     * Throws coin to decide which player starts the game first.
     *
     * @return {@link GameParticipant}
     */
    public GameParticipant toss() {
        int faceOfCoin = new Random().nextInt(POSSIBILITIES);
        return faceOfCoin == 0 ? computer : user;
    }
}
