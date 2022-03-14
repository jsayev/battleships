package com.guestline.pre_interview.battleships.errors;

/**
 * @author Jovhar Isayev
 */
public class NonExistentCoordinateError extends Exception {
    public static final String NON_EXISTENT_COORDINATE_MESSAGE = "Invalid coordinates!";
    public static final String ALREADY_HIT_COORDINATE_MESSAGE = "Coordinates have already been hit!";

    public NonExistentCoordinateError(String message) {
        super(message);
    }
}
