package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.Ships;

import java.util.List;

/**
 * @author Jovhar Isayev
 */
interface ShipCoordinator {
    List<String> generateShipCoordinates(int[][] gameMap, Ships[] ships);
}
