package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.Game;
import com.guestline.pre_interview.battleships.arsenal.Ship;
import com.guestline.pre_interview.battleships.errors.InvalidCoordinateInput;

import java.util.*;

/**
 * @author Jovhar Isayev
 */
public final class User extends GameParticipant {
    private final Scanner input;

    public User(String name, Scanner input) {
        super(name);
        this.input = input;
        this.shipCoordinates = placeShips(Ship.values());
    }

    @Override
    public void play() {
        System.out.println("Please input your desired coordinates: ");
    }

    @Override
    public EnumMap<Ship, List<String>> placeShips(Ship[] ships) {
        EnumMap<Ship, List<String>> shipCoordinates = new EnumMap<>(Ship.class);
        for (Ship ship : ships) {
            for (int count = 0; count < ship.count; count++) {
                ArrayList<String> coordinates = new ArrayList<>();
                shipCoordinates.values().forEach(coordinates::addAll);

                boolean startCycle = true;
                while (startCycle) {
                    System.out.printf("""
                            Placing following ship:
                            - name: %s
                            - length: %d%n""", ship.name().toUpperCase(), ship.length);
                    System.out.println("Please type in the cell coordinates(A1,A2,A3 etc.) with comma between them: ");
                    String[] inputCoordinates = input.nextLine().split(",");
                    if (validateUserInput(inputCoordinates, ship.length, coordinates)) {
                        List<String> existingCoordinates = shipCoordinates.putIfAbsent(ship,new ArrayList<>(Arrays.stream(inputCoordinates).toList()));
                        if (existingCoordinates != null) {
                            existingCoordinates.addAll(List.of(inputCoordinates));
                            shipCoordinates.put(ship, existingCoordinates);
                        }
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
                            throw new InvalidCoordinateInput(InvalidCoordinateInput.NON_HORIZONTAL);
                        }
                    } else {
                        if (!(currentLetterCoordinate + 1 == nextLetterCoordinate && currentNumberCoordinate == Integer.parseInt(inputCoordinates[i + 1].substring(1)))) {
                            throw new InvalidCoordinateInput(InvalidCoordinateInput.NON_VERTICAL);
                        }
                    }
                }

                if (registeredShipCoordinates.size() > 0 && registeredShipCoordinates.contains(inputCoordinates[i]))
                    throw new InvalidCoordinateInput(InvalidCoordinateInput.COORDINATE_EXISTS);
            }
        } catch (InvalidCoordinateInput err) {
            System.err.println(err.getMessage());
            return false;
        } catch (NumberFormatException err) {
            System.err.println(InvalidCoordinateInput.INVALID_NUMBER);
            return false;
        }
        return true;
    }
}
