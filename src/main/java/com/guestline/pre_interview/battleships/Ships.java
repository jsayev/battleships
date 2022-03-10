package com.guestline.pre_interview.battleships;

/**
 * @author Jovhar Isayev
 */
enum Ships {
    BATTLESHIP(5, 1),
    DESTROYER(4, 2);

    public int shipLength;
    public int shipCount;

    Ships(int shipLength, int shipCount) {
        this.shipLength = shipLength;
        this.shipCount = shipCount;
    }
}
