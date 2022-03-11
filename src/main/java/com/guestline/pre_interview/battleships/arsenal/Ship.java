package com.guestline.pre_interview.battleships.arsenal;

/**
 * @author Jovhar Isayev
 */
public enum Ship {
    BATTLESHIP(5, 1),
    DESTROYER(4, 2);

    public final int shipLength;
    public final int shipCount;

    Ship(int shipLength, int shipCount) {
        this.shipLength = shipLength;
        this.shipCount = shipCount;
    }
}
