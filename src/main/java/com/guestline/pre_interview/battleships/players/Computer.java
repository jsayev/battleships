package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.Game;
import com.guestline.pre_interview.battleships.arsenal.Ship;
import com.guestline.pre_interview.battleships.game_utilities.CoinTosser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jovhar Isayev
 */
public final class Computer extends GameParticipant {
    public Computer(String name) {
        super(name);
        super.shipCoordinates = placeShips(gameMap, Ship.values());
    }

    @Override
    public void play() {
        System.out.println("Computer's turn! It played: ");
    }

    @Override
    public List<String> placeShips(int[][] gameMap, Ship[] ships) {
        List<String> coordinates = new ArrayList<>();
        for (Ship ship : ships) {
            for (int shipCount = 0; shipCount < ship.shipCount; shipCount++) {
                coordinates.addAll(makeCoordinates(ship));
            }
        }
        return coordinates;
    }

    private List<String> makeCoordinates(Ship ship) {
        ArrayList<String> coordinates = new ArrayList<>();
        String startCoordinate = String.format(
                "%c%d",
                (char) (new Random().nextInt(Game.MAP_ROW_SIZE) + 'A'),
                new Random().nextInt(Game.MAP_COLUMN_SIZE));

        boolean startCycle = true;
        while (startCycle) {
            boolean alongHorizontalLine = new CoinTosser<>(true, false).toss();
            if (isPlacementPossible(startCoordinate, ship, alongHorizontalLine)) {
                coordinates.addAll(generatePossibleCoordinates(startCoordinate, ship, alongHorizontalLine));
                startCycle = false;
            } else if (isPlacementPossible(startCoordinate, ship, alongHorizontalLine)) {
                coordinates.addAll(generatePossibleCoordinates(startCoordinate, ship, alongHorizontalLine));
                startCycle = false;
            } else {
                startCoordinate = String.format(
                        "%c%d",
                        (char) (new Random().nextInt(Game.MAP_ROW_SIZE) + 'A'),
                        new Random().nextInt(Game.MAP_COLUMN_SIZE));
            }
        }
        return coordinates;
    }

    private boolean isPlacementPossible(String startCoordinate, Ship ship, boolean alongHorizontalLine) {
        int letterCoordinate = startCoordinate.charAt(0) - Game.START_COORDINATE_LETTER;
        int numberCoordinate = Integer.parseInt(startCoordinate.substring(1));

        if (alongHorizontalLine && ship.shipLength + numberCoordinate > Game.MAP_COLUMN_SIZE) {
            return false;
        } else if (ship.shipLength + letterCoordinate > Game.MAP_ROW_SIZE) {
            return false;
        }

        if (shipCoordinates.size() == 0) return true;

        List<String> possibleCoordinates = generatePossibleCoordinates(startCoordinate, ship, alongHorizontalLine);
        for (String possibleCoordinate : possibleCoordinates) {
            if (shipCoordinates.indexOf(possibleCoordinate) != -1) {
                return false;
            }
        }
        return true;
    }

    private boolean isHorizontalPlacementPossible(String startCoordinate, Ship ship) {
        int numberCoordinate = Integer.parseInt(startCoordinate.substring(1));
        if (ship.shipLength + numberCoordinate > Game.MAP_COLUMN_SIZE) {
            return false;
        }
        if (shipCoordinates.size() == 0) return true;
        List<String> possibleCoordinates = generatePossibleCoordinates(startCoordinate, ship, true);
        for (String possibleCoordinate : possibleCoordinates) {
            if (shipCoordinates.indexOf(possibleCoordinate) != -1) {
                return false;
            }
        }
        return true;
    }

    private List<String> generatePossibleCoordinates(String startCoordinate, Ship ship, boolean alongHorizontalLine) {
        char letterCoordinate = startCoordinate.charAt(0);
        int numberCoordinate = Integer.parseInt(startCoordinate.substring(1));

        List<String> possibleCoordinates = new ArrayList<>(ship.shipLength);
        possibleCoordinates.add(startCoordinate);
        for (int len = 1; len < ship.shipLength; len++) {
            possibleCoordinates.add(String.format(
                    "%c%d",
                    alongHorizontalLine ? letterCoordinate : letterCoordinate + len,
                    alongHorizontalLine ? numberCoordinate + len : numberCoordinate));
        }
        return possibleCoordinates;
    }
}
