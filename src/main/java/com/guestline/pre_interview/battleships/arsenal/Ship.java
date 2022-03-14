package com.guestline.pre_interview.battleships.arsenal;

/**
 * @author Jovhar Isayev
 */
public enum Ship {
    BATTLESHIP(5, 1),
    DESTROYER(4, 2);

    public final int length;
    public final int count;

    Ship(int length, int count) {
        this.length = length;
        this.count = count;
    }
}
