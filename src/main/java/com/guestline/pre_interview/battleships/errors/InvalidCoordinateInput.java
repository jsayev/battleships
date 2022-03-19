package com.guestline.pre_interview.battleships.errors;

/**
 * @author Jovhar Isayev
 */
public class InvalidCoordinateInput extends Throwable {
    public static final String INVALID_COORDINATE_LENGTH = "Cell coordinate should be 2 or 3 characters long!";
    public static final String UNSUPPORTED_CHARACTERS = "Coordinate is out of game map boundaries!";
    public static final String SHIP_LENGTH_REQUIREMENTS = "Coordinates doesn't meet ship length requirements!";
    public static final String COORDINATE_EXISTS = "Cell is already taken by another ship!";
    public static final String INVALID_NUMBER = "Invalid number coordinate!";
    public static final String NON_HORIZONTAL = "Coordinates doesn't follow horizontal line!";
    public static final String NON_VERTICAL = "Coordinates doesn't follow vertical line!";

    public InvalidCoordinateInput(String message) {
        super(message);
    }
}
