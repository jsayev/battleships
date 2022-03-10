package com.guestline.pre_interview.battleships;

/**
 * @author Jovhar Isayev
 */
class Coordinate {
    private String y;
    private int x;

    Coordinate(String y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return y + x;
    }
}
