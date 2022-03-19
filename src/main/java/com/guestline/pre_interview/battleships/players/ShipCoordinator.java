package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.arsenal.Ship;

import java.util.EnumMap;
import java.util.List;

/**
 * @author Jovhar Isayev
 */
interface ShipCoordinator {
    EnumMap<Ship,List<String>> placeShips(Ship[] ships);
}
