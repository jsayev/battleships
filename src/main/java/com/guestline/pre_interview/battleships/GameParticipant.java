package com.guestline.pre_interview.battleships;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jovhar Isayev
 */
final class GameParticipant implements Gamer {
    private final String name;
    private final List<Coordinate> alreadyHitSpots;
    private final List<Coordinate> availableSpotsToHit;
    private int[][] gameMap;
    private GameParticipant opponent;

    GameParticipant(String name, GamerType gamerType, int mapRows, int mapColumns) {
        this.name = name;
        this.availableSpotsToHit = generateSpotsToHit(mapRows, mapColumns);
        this.alreadyHitSpots = new ArrayList<Coordinate>();
        this.gameMap = switch (gamerType) {
            case COMPUTER -> buildRandomizedMap(mapRows, mapColumns);
            case USER -> buildCustomizedMap(mapRows, mapColumns);
        };
    }

    private int[][] buildCustomizedMap(int mapRows, int mapColumns) {
        return new int[0][];
    }

    private List<Coordinate> generateSpotsToHit(int mapRows, int mapColumns) {
        char startLetter = 'A';
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int row = 0; row < mapRows; row++) {
            for (int column = 0; column < mapColumns; column++) {
                coordinates.add(new Coordinate(String.valueOf((char) (startLetter + row)), column + 1));
            }
        }
        return coordinates;
    }

    private int[][] buildRandomizedMap(int mapRows, int mapColumns) {
        int[][] preGameMap = new int[mapRows][mapColumns];
        return null;
    }

    @Override
    public void play() {

    }

    public void addOpponent(GameParticipant gameParticipant) {
        this.opponent = gameParticipant;
    }
}
