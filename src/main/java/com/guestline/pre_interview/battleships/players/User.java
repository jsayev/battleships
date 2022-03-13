package com.guestline.pre_interview.battleships.players;

import com.guestline.pre_interview.battleships.arsenal.Ship;

import java.util.List;
import java.util.Scanner;

/**
 * @author Jovhar Isayev
 */
public final class User extends GameParticipant {
    private final Scanner input;

    public User(String name, Scanner input) {
        super(name);
        super.shipCoordinates = placeShips(gameMap, Ship.values());
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
    public List<String> placeShips(int[][] gameMap, Ship[] ships) {
        //todo ask for user input to generate ships at desired locations
        return null;
    }

    private boolean orderToHitOnCoordinates(String coordinates) {
        availableSpotsToHit.remove(coordinates);
        alreadyHitSpots.add(coordinates);
//        return opponent.shipCoordinates.contains(coordinates);
        return false;
    }
}
