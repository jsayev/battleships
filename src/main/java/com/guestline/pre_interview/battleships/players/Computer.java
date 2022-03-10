package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.Ships;

import java.util.List;

/**
 * @author Jovhar Isayev
 */
public final class Computer extends GameParticipant {
    public Computer(String name) {
        super(name);
        super.shipCoordinates = generateShipCoordinates(gameMap, Ships.values());
    }

    @Override
    public void play() {
        System.out.println("Computer's turn! It played: ");
    }

    @Override
    public List<String> generateShipCoordinates(int[][] gameMap, Ships[] ships) {
        //todo generate map with ships
        return null;
    }

    private boolean orderToHitOnCoordinates(String coordinates) {
        return false;
    }
}
