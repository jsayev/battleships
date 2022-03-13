package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jovhar Isayev
 */
public abstract class GameParticipant implements Player, ShipCoordinator {
    protected final String name;
    protected final List<String> alreadyHitSpots;
    protected final List<String> availableSpotsToHit;
    protected int[][] gameMap;
    protected GameParticipant opponent;
    protected List<String> shipCoordinates;

    GameParticipant(String name) {
        this.name = name;
        this.availableSpotsToHit = generateAvailableSpotsToHit(Game.MAP_ROW_SIZE, Game.MAP_COLUMN_SIZE);
        this.gameMap = new int[Game.MAP_ROW_SIZE][Game.MAP_COLUMN_SIZE];
        this.alreadyHitSpots = new ArrayList<>();
        this.shipCoordinates = new ArrayList<>();
    }

    private List<String> generateAvailableSpotsToHit(int mapRows, int mapColumns) {
        ArrayList<String> coordinates = new ArrayList<>();
        for (int row = 0; row < mapRows; row++) {
            for (int column = 0; column < mapColumns; column++) {
                coordinates.add(String.format("%c%d", (char) (Game.START_COORDINATE_LETTER + row), column + 1));
            }
        }
        return coordinates;
    }

    public void setOpponent(GameParticipant gameParticipant) {
        if (this.opponent == null) {
            this.opponent = gameParticipant;
            gameParticipant.opponent = this;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
