package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.Game;
import com.guestline.pre_interview.battleships.arsenal.Ship;

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
                (char) (new Random().nextInt(Game.MAP_ROWS) + 'A'),
                new Random().nextInt(Game.MAP_COLUMN));
        while (!shipCoordinates.contains(startCoordinate)) {
            if (isHorizontalPlacementPossible(startCoordinate, ship)) {
                shipCoordinates.
            } else if (isVerticalPlacementPossible(startCoordinate, ship)) {

            } else {
                startCoordinate = String.format(
                        "%c%d",
                        (char) (new Random().nextInt(Game.MAP_ROWS) + 'A'),
                        new Random().nextInt(Game.MAP_COLUMN));
            }
        }
        return coordinates;
    }

    private boolean isHorizontalPlacementPossible(String startCoordinate, Ship ship) {
        int numberCoordinate = Integer.parseInt(startCoordinate.substring(1));
        if (ship.shipLength + numberCoordinate > Game.MAP_COLUMN) {
            return false;
        }
        char letterCoordinate = startCoordinate.charAt(0);
        String[] possibleCoordinates = new String[ship.shipLength];
        possibleCoordinates[0] = startCoordinate;
        for (int len = 1; len < ship.shipLength; len++) {
            possibleCoordinates[len] = String.format("%c%d", letterCoordinate, numberCoordinate + len);
        }
        // todo implement logic to check all elements in possible coordinates if any of them is available in shipcoordinates elements
    }
}
