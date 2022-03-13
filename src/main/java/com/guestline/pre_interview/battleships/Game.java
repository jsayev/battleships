package com.guestline.pre_interview.battleships;

import com.guestline.pre_interview.battleships.game_utilities.CoinTosser;
import com.guestline.pre_interview.battleships.players.Computer;
import com.guestline.pre_interview.battleships.players.GameParticipant;
import com.guestline.pre_interview.battleships.players.User;

import java.util.Scanner;

import static java.lang.System.out;

/**
 * Start point for the BattleShips game.
 *
 * @author Jovhar Isayev
 */
public final class Game {
    public static final int MAP_ROW_SIZE = 10;
    public static final int MAP_COLUMN_SIZE = 10;
    public static final char START_COORDINATE_LETTER = 'A';
    private static final Scanner INPUT = new Scanner(System.in);
    private final GameParticipant computer;
    private final GameParticipant user;

    Game(GameParticipant computer, GameParticipant user) {
        this.computer = computer;
        this.user = user;
    }

    public static void main(String[] args) {
        out.println("Welcome to BattleShips game!");

        String computerName = System.getenv("USERNAME") + "'s pc";
        GameParticipant computer = new Computer(computerName);

        out.print("Please input your name: ");
        GameParticipant user = new User(INPUT.nextLine(), INPUT);

        Game game = new Game(computer, user);
        game.start();
    }

    private void start() {
        computer.setOpponent(user);
        GameParticipant startingGamer = new CoinTosser<>(computer, user).toss();
        out.println(startingGamer + " starting first.");
        startingGamer.play();
    }
}
