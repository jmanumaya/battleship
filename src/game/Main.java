package game;

import java.util.Scanner;

public class Main {

	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {
		// Attribute that defines the value of the row
		char row;
		// Attribute that defines the value of the column
		int column;
		// Attribute that defines the value of the count of player's ships
		int countPlayerShips = 0;
		// Attribute that defines the value of the player's points
		int pointsPlayer = 0;
		// Attribute that defines the value of the machine's points
		int pointsMachine = 0;
		// Attribute that defines the value of the turns. The first turn is the player
		// and the second turn is the machine
		int turn = 1;
		// Attribute that defines the final result of a machine's hit or player's hit
		int result;

		// Constant that indicates the number of ships to be placed
		final int NUM_SHIPS = 4;

		// We created the Battle Ship Game
		BattleShipGame game = new BattleShipGame();

		// Welcome to the game
		System.out.println("****************** WELCOME TO BATTLESHIP GAME!!! ******************\n");

		// Initialize all boards
		game.initializeBoard(game.getMachineTable());
		game.initializeBoard(game.getMachineTableShips());
		game.initializeBoard(game.getPlayerTable());
		game.initializeBoard(game.getPlayerTableShips());

		// We placed the ships of the player
		game.tablePaint(game.getPlayerTableShips());
		System.out.println("Place your ships");
		do {
			System.out.println("Introduce the row: ");
			row = reader.nextLine().toUpperCase().charAt(0);
			System.out.println("Introduce the column: ");
			column = reader.nextInt();
			reader.nextLine();
			if (game.movePlayerShips(row, column, game.getPlayerTableShips())) {
				countPlayerShips++;
			} else {
				System.err.println("The ship could not be placed in position");
				System.out.println();
			}
			game.tablePaint(game.getPlayerTableShips());
		} while (countPlayerShips < NUM_SHIPS);

		// We placed the ships of the machine
		game.moveMachineShips();
		System.out.println("The machine has already placed its ships\n\n");

		System.out.println("****************** START ******************");
		// As long as neither player has sunk all of the opponent's ships, the game
		// continues
		while (pointsMachine < NUM_SHIPS && pointsPlayer < NUM_SHIPS) {
			if (turn == 1) {
				// Player turn
				System.out.println("\n****************** PLAYER TURN ******************");
				System.out.println("Find the opponent's ships");
				game.tablePaint(game.getPlayerTable());
				System.out.println("Your ships");
				game.tablePaint(game.getPlayerTableShips());
				System.out.println("Introduce the row: ");
				row = reader.nextLine().toUpperCase().charAt(0);
				System.out.println("Introduce the column: ");
				column = reader.nextInt();
				reader.nextLine();
				// We check whether a ship has sunk or not
				result = game.hitShipTurnPlayer(row, column);
				if (result == 1) {
					pointsPlayer++;
					System.out.println("SUNKEN SHIP");
				} else if (result == 0) {
					System.out.println("WATER");
					// If you haven't sunk a ship, the turn goes to the machine
					turn = 2;
				} else {
					System.out.println("The position has already been discovered");
					System.out.println("Choose another position that has not been discovered");
				}
				game.tablePaint(game.getPlayerTable());
			} else {
				// Machine turn
				System.out.println("\n****************** MACHINE TURN ******************");
				// We check whether a ship has sunk or not
				do {
					result = game.hitShipTurnMachine();
					if (result == 1) {
						pointsMachine++;
						System.out.println("SUNKEN SHIP");
					} else if (result == 0) {
						System.out.println("WATER");
						// If you haven't sunk a ship, the turn goes to the player
						turn = 1;
					}
				} while (result != 1 && result != 0);
				game.tablePaint(game.getMachineTable());
			}
		}

		System.out.println("\n\n");
		// If the machine has the amount of the total number of ships it has won the
		// game, if it has the player has won the game
		if (pointsMachine == NUM_SHIPS) {
			System.out.println("VICTORY OF THE MACHINE!!!");
		} else {
			System.out.println("VICTORY OF THE PLAYER!!!");
		}

		// We show all the boards at the end of the game
		System.out.println("****************** END OF THE GAME ******************\nRESULTS:");
		System.out.println("Player ship board");
		game.tablePaint(game.getPlayerTableShips());
		System.out.println("Machine ship board");
		game.tablePaint(game.getMachineTableShips());
		System.out.println("Player's game board");
		game.tablePaint(game.getPlayerTable());
		System.out.println("Machine's game board");
		game.tablePaint(game.getMachineTable());

		// We close the Scanner
		reader.close();
	}
}
