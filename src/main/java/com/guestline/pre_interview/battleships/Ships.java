package com.guestline.pre_interview.battleships;

/**
 * @author Jovhar Isayev
 */
public enum Ships {
    BATTLESHIP(5, 1),
    DESTROYER(4, 2);

    public final int shipLength;
    public final int shipCount;

    Ships(int shipLength, int shipCount) {
        this.shipLength = shipLength;
        this.shipCount = shipCount;
    }
}
