package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.Game;
import com.guestline.pre_interview.battleships.arsenal.Ship;
import com.guestline.pre_interview.battleships.game_utilities.CoinTosser;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author Jovhar Isayev
 */
public final class Computer extends GameParticipant {
    public Computer(String name) {
        super(name);
        super.shipCoordinates = placeShips(Ship.values());
    }

    @Override
    public void play() {
        System.out.println(this + " plays");
//        String pickedCoordinate = availableCoordinatesToHit.get(new Random().nextInt(availableCoordinatesToHit.size()));
//        boolean startShooting = true;
//        while (startShooting) {
//            if (this.opponent.shipCoordinates.values().) {
//                damagedCoordinatesOfOpponent.add(pickedCoordinate);
//                pickedCoordinate = pickLogicalCoordinate(pickedCoordinate);
//            } else {
//                startShooting = false;
//            }
//        }
//        this.opponent.play();
    }

    // TODO  to be completed
    private String pickLogicalCoordinate(final String pickedCoordinate) {
        String logicalCoordinate = null;
        char letter = pickedCoordinate.charAt(0);
        int number = Integer.parseInt(pickedCoordinate.substring(1));
        if (letter == Game.START_COORDINATE_LETTER && number == 1) {

        } else if (letter == Game.START_COORDINATE_LETTER && number == 10) {

        } else if (letter == Game.START_COORDINATE_LETTER + 9 && number == 1) {

        } else if (letter == Game.START_COORDINATE_LETTER + 9 && number == 10) {

        } else if (letter == Game.START_COORDINATE_LETTER && number > 1 && number < 10) {

        } else if (letter == Game.START_COORDINATE_LETTER + 9 && number > 1 && number < 10) {

        } else if (letter > Game.START_COORDINATE_LETTER && letter < Game.START_COORDINATE_LETTER + 9 && number == 1) {

        } else if (letter > Game.START_COORDINATE_LETTER && letter < Game.START_COORDINATE_LETTER + 9 && number == 10) {

        } else {

        }
        damagedCoordinatesOfOpponent.add(logicalCoordinate);
        return logicalCoordinate;
    }

    @Override
    public EnumMap<Ship, List<String>> placeShips(Ship[] ships) {
        EnumMap<Ship, List<String>> candidateCoordinates = new EnumMap<>(Ship.class);
        for (Ship ship : ships) {
            for (int count = 0; count < ship.count; count++) {
                List<String> existingCoordinates = candidateCoordinates.putIfAbsent(ship, makeCoordinates(ship, candidateCoordinates));
                if (existingCoordinates != null) {
                    existingCoordinates.addAll(makeCoordinates(ship, candidateCoordinates));
                    candidateCoordinates.put(ship, existingCoordinates);
                }
            }
        }
        return candidateCoordinates;
    }

    private List<String> makeCoordinates(Ship ship, EnumMap<Ship, List<String>> candidateCoordinates) {
        ArrayList<String> coordinatesOfShip = new ArrayList<>();
        String startCoordinate = String.format(
                "%c%d",
                (char) (new Random().nextInt(Game.MAP_ROW_SIZE) + 'A'),
                new Random().nextInt(Game.MAP_COLUMN_SIZE) + 1);

        boolean startCycle = true;
        while (startCycle) {
            boolean alongHorizontalLine = new CoinTosser<>(true, false).toss();
            if (isPlacementPossible(startCoordinate, ship, alongHorizontalLine, candidateCoordinates)) {
                coordinatesOfShip.addAll(generatePossibleCoordinates(startCoordinate, ship, alongHorizontalLine));
                startCycle = false;
            } else {
                startCoordinate = String.format(
                        "%c%d",
                        (char) (new Random().nextInt(Game.MAP_ROW_SIZE) + 'A'),
                        new Random().nextInt(Game.MAP_COLUMN_SIZE) + 1);
            }
        }
        return coordinatesOfShip;
    }

    private boolean isPlacementPossible(String startCoordinate, Ship ship, boolean alongHorizontalLine, EnumMap<Ship, List<String>> candidateCoordinates) {
        int letterCoordinate = startCoordinate.charAt(0) - Game.START_COORDINATE_LETTER;
        int numberCoordinate = Integer.parseInt(startCoordinate.substring(1));

        if ((alongHorizontalLine && ship.length + numberCoordinate > Game.MAP_COLUMN_SIZE) || (ship.length + letterCoordinate > Game.MAP_ROW_SIZE)) {
            return false;
        }

        if (candidateCoordinates.values().size() == 0) return true;

        List<String> possibleCoordinates = generatePossibleCoordinates(startCoordinate, ship, alongHorizontalLine);
        for (List<String> value : candidateCoordinates.values()) {
            for (String coordinate : value) {
                for (String possibleCoordinate : possibleCoordinates) {
                    if (possibleCoordinate.equals(coordinate)) return false;
                }
            }
        }
        return true;
    }

    private List<String> generatePossibleCoordinates(String startCoordinate, Ship ship, boolean alongHorizontalLine) {
        char letterCoordinate = startCoordinate.charAt(0);
        int numberCoordinate = Integer.parseInt(startCoordinate.substring(1));

        List<String> possibleCoordinates = new ArrayList<>(ship.length);
        possibleCoordinates.add(startCoordinate);
        for (int len = 1; len < ship.length; len++) {
            possibleCoordinates.add(String.format(
                    "%c%d",
                    alongHorizontalLine ? letterCoordinate : letterCoordinate + len,
                    alongHorizontalLine ? numberCoordinate + len : numberCoordinate));
        }
        return possibleCoordinates;
    }
}
