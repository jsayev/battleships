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
        super.shipCoordinates = placeShips(Ship.values());
        this.input = input;
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
                    System.out.printf("Placing following ship:\n" +
                            "- name: %s\n" +
                            "- length: %d\n", ship.name().toUpperCase(), ship.length);
                    System.out.printf("Please type in coordinates in the form of \"A3,A4,A5,...\" : ", ship.name().toLowerCase(), ship.length);
                    String[] inputCoordinates = input.nextLine().split(",");
                    if (validateUserInput(inputCoordinates, ship.length)) {
                        shipCoordinates.addAll(List.of(inputCoordinates));
                        startCycle = false;
                    }
                }
            }
        }
        return shipCoordinates;
    }

    private boolean validateUserInput(String[] inputCoordinates, int shipLength) {
        try {
            if (inputCoordinates.length != shipLength) {
                throw new InvalidCoordinateInput(InvalidCoordinateInput.SHIP_LENGTH_REQUIREMENTS);
            }
            //todo make sure coordinates follow logical order either A1,A2,A3 or A1,B1,C1
            for (int i = 0; i < inputCoordinates.length; i++) {
                if (inputCoordinates[i].length() > 3 || inputCoordinates[i].length() < 2) {
                    throw new InvalidCoordinateInput(InvalidCoordinateInput.INVALID_COORDINATE_LENGTH);
                }

                int letterCoordinate = inputCoordinates[i].charAt(0);
                int numberCoordinate = Integer.parseInt(inputCoordinates[i].substring(1));
                int letterDifference = letterCoordinate - Game.START_COORDINATE_LETTER;

                if ((letterDifference < 0 || letterDifference > 9) || (numberCoordinate > Game.MAP_COLUMN_SIZE | numberCoordinate < 1)) {
                    throw new InvalidCoordinateInput(InvalidCoordinateInput.UNSUPPORTED_CHARACTERS);
                }
            }

        } catch (InvalidCoordinateInput err) {
            System.err.println(err.getMessage());
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
