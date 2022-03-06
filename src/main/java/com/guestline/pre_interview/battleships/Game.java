package com.guestline.pre_interview.battleships;

import java.util.Scanner;

import static java.lang.System.out;

/**
 * Start point for the BattleShips game.
 *
 * @author Jovhar Isayev
 */
final class Game {
    private static final Scanner input = new Scanner(System.in);
    private Player computer;
    private Player user;

    Game(Player computer, Player user) {
        this.computer = computer;
        this.user = user;
    }

    public static void main(String[] args) {
        out.println("Welcome to BattleShips game!");
        out.print("Please input your name: ");

        String playerName = input.nextLine();
        String computerName = System.getenv("USERNAME") + "'s pc";
        Game game = new Game(new Computer(computerName), new User(playerName));
        game.start();
    }

    private void start() {
        Player startingPlayer = new CoinTosser(computer, user).toss();
        startingPlayer.play();
    }
}
