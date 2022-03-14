package com.guestline.pre_interview.battleships.errors;

/**
 * @author Jovhar Isayev
 */
public class InvalidCoordinateInput extends Throwable {
    public static final String INVALID_COORDINATE_LENGTH = "Cell coordinate should be 2 or 3 characters long!";
    public static final String UNSUPPORTED_CHARACTERS = "Coordinate contains unsupported characters!";
    public static final String SHIP_LENGTH_REQUIREMENTS = "Input coordinates doesn't meet ship length requirements!";

    public InvalidCoordinateInput(String message) {
        super(message);
    }
}
