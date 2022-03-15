package com.guestline.pre_interview.battleships.errors;

/**
 * @author Jovhar Isayev
 */
public class InvalidCoordinateInput extends Throwable {
    public static final String INVALID_COORDINATE_LENGTH = "Cell coordinate should be 2 or 3 characters long!";
    public static final String UNSUPPORTED_CHARACTERS = "Coordinate is out of game map boundaries!";
    public static final String SHIP_LENGTH_REQUIREMENTS = "Input coordinates doesn't meet ship length requirements!";
    public static final String NON_LOGICAL_ORDER = "Cells don't follow logical order!";
    public static final String NOT_HORIZONTAL = "Cells don't follow horizontal order!";
    public static final String NOT_VERTICAL = "Cells don't follow vertical order!";
    public static final String COORDINATE_EXISTS = "Cell is already taken by another ship!";

    public InvalidCoordinateInput(String message) {
        super(message);
    }
}
