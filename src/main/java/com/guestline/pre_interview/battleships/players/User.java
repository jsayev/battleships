package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.Game;
import com.guestline.pre_interview.battleships.arsenal.Ship;
import com.guestline.pre_interview.battleships.errors.InvalidCoordinateInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jovhar Isayev
 */
public final class User extends GameParticipant {
    private final Scanner input;

    public User(String name, Scanner input) {
        super(name);
        this.input = input;
        super.shipCoordinates = placeShips(Ship.values());
    }

    @Override
    public void play() {
        System.out.println("Please input your desired coordinates: ");
//        String inputCoordinates = input.nextLine();
//        while (!availableSpotsToHit.contains(inputCoordinates)) {
//            System.err.println("Invalid coordinates! Try again: ");
//            inputCoordinates = input.nextLine();
//        }
//        while (orderToHitOnCoordinates(inputCoordinates)) {
//            System.out.println("Target hit! Next coordinates: ");
//            inputCoordinates = input.nextLine();
//        }
//        opponent.play();
    }

    @Override
    public List<String> placeShips(Ship[] ships) {
        List<String> shipCoordinates = new ArrayList<>();
        for (Ship ship : ships) {
            for (int count = 0; count < ship.count; count++) {
                boolean startCycle = true;
                while (startCycle) {
                    System.out.println("=".repeat(5) + " Ship placement phase " + "=".repeat(5));
                    System.out.printf("Placing following ship:\n" +
                            "- name: %s\n" +
                            "- length: %d\n", ship.name().toUpperCase(), ship.length);
                    System.out.printf("Please type in the cell coordinates(A1,J10 etc.) with comma between them: ");
                    String[] inputCoordinates = input.nextLine().split(",");
                    if (validateUserInput(inputCoordinates, ship.length, shipCoordinates)) {
                        shipCoordinates.addAll(List.of(inputCoordinates));
                        startCycle = false;
                    }
                }
            }
        }
        return shipCoordinates;
    }

    private boolean validateUserInput(String[] inputCoordinates, int shipLength, List<String> registeredShipCoordinates) {
        try {
            if (inputCoordinates.length != shipLength) {
                throw new InvalidCoordinateInput(InvalidCoordinateInput.SHIP_LENGTH_REQUIREMENTS);
            }

            boolean shouldFollowHorizontalLine = inputCoordinates[0].charAt(0) == inputCoordinates[1].charAt(0);

            for (int i = 0; i < inputCoordinates.length; i++) {
                if (inputCoordinates[i].length() > 3 || inputCoordinates[i].length() < 2) {
                    throw new InvalidCoordinateInput(InvalidCoordinateInput.INVALID_COORDINATE_LENGTH);
                }

                int currentLetterCoordinate = inputCoordinates[i].charAt(0);
                int currentNumberCoordinate = Integer.parseInt(inputCoordinates[i].substring(1));
                boolean isNumberOutOfMapLimit = currentNumberCoordinate > Game.MAP_COLUMN_SIZE || currentNumberCoordinate < 1;
                boolean isLetterOutOfMapLimit = currentLetterCoordinate - Game.START_COORDINATE_LETTER < 0 || currentLetterCoordinate - Game.START_COORDINATE_LETTER > 9;

                if (isLetterOutOfMapLimit || isNumberOutOfMapLimit) {
                    throw new InvalidCoordinateInput(InvalidCoordinateInput.UNSUPPORTED_CHARACTERS);
                }

                if (i <= inputCoordinates.length - 2) {
                    char nextLetterCoordinate = inputCoordinates[i + 1].charAt(0);
                    int nextNumberCoordinate = Integer.parseInt(inputCoordinates[i + 1].substring(1));
                    if (shouldFollowHorizontalLine) {
                        if (!(currentLetterCoordinate == nextLetterCoordinate && currentNumberCoordinate + 1 == nextNumberCoordinate)) {
                            throw new InvalidCoordinateInput(InvalidCoordinateInput.NOT_HORIZONTAL);
                        }
                    } else {
                        if (!(currentLetterCoordinate + 1 == nextLetterCoordinate && currentNumberCoordinate == Integer.parseInt(inputCoordinates[i + 1].substring(1)))) {
                            throw new InvalidCoordinateInput(InvalidCoordinateInput.NOT_VERTICAL);
                        }
                    }
                }

                if (registeredShipCoordinates.size() > 0 && registeredShipCoordinates.contains(inputCoordinates[i]))
                    throw new InvalidCoordinateInput(InvalidCoordinateInput.COORDINATE_EXISTS);
            }
        } catch (InvalidCoordinateInput | NumberFormatException err) {
            if(err instanceof NumberFormatException){
                System.err.println("Invalid number coordinate!");
            }else{
                System.err.println(err.getMessage());
            }
            return false;
        }
        return true;
    }

    private boolean orderToHitOnCoordinates(String coordinates) {
        availableSpotsToHit.remove(coordinates);
        alreadyHitSpots.add(coordinates);
//        return opponent.shipCoordinates.contains(coordinates);
        return false;
    }
}
