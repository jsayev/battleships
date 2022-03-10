package com.guestline.pre_interview.battleships;

import java.util.Scanner;

import static java.lang.System.out;

/**
 * Start point for the BattleShips game.
 *
 * @author Jovhar Isayev
 */
final class Game {
    public static final int MAP_ROWS = 10;
    public static final int MAP_COLUMN = 10;
    private static final Scanner input = new Scanner(System.in);
    private final GameParticipant[] gameParticipants = new GameParticipant[2];

    Game(GameParticipant... gameParticipants) {
        this.gameParticipants[0] = gameParticipants[0];
        this.gameParticipants[1] = gameParticipants[1];
    }

    public static void main(String[] args) {
        out.println("Welcome to BattleShips game!");

        String computerName = System.getenv("USERNAME") + "'s pc";
        GameParticipant computer = new GameParticipant(computerName, GamerType.COMPUTER, MAP_ROWS, MAP_COLUMN);

        out.print("Please input your name: ");
        GameParticipant user = new GameParticipant(input.nextLine(), GamerType.USER, MAP_ROWS , MAP_COLUMN);

        Game game = new Game(computer, user);
        game.start();
    }

    private void start() {
        designateOpponents();
        GameParticipant startingGamer = CoinTosser.toss(gameParticipants);
        startingGamer.play();
    }

    private void designateOpponents() {
        gameParticipants[0].addOpponent(gameParticipants[1]);
        gameParticipants[1].addOpponent(gameParticipants[0]);
    }
}
