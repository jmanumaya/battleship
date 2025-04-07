package game;

import java.util.Arrays;
import java.util.Random;

public class Battleship {

	// Constants for board elements
	private static final char WATER = '-';
	private static final char SHIP = 'B';
	private static final char HIT = 'X';
	private static final char MISS = 'O';

	// Game boards for player and machine
	private static char machineBoard1[][] = new char[10][10];  
	private static char machineBoard2[][] = new char[10][10];  

	private static char playerBoard1[][] = new char[10][10];  
	private static char playerBoard2[][] = new char[10][10];  


	/**
	 * Prints the game board in a structured format.
	 *
	 * @param table The board to print.
	 */
	public static void tablePaint(char[][] table) {
		initializeBoard(table);

		char letterA = 'A';

		// Print column numbers
		for (int i = 1; i <= table.length; i++) {
			System.out.print("\t" + i);
		}
		System.out.println();

		// Print rows with letter labels
		for (int i = 0; i < table.length; i++) {
			System.out.print((char) (letterA + i) + "\t");

			for (int j = 0; j < table.length; j++) {
				System.out.print(table[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * Initializes the game board by filling it with water ('-').
	 *
	 * @param table The board to initialize.
	 */
	public static void initializeBoard(char[][] table) {
		for (int i = 0; i < table.length; i++) {
			Arrays.fill(table[i], WATER);
		}
	}

	/**
	 * Randomly places a given number of ships on the board.
	 *
	 * @param board    The game board where ships will be placed.
	 * @param numBoats The number of ships to be placed.
	 */
	public static void generateBoats(char[][] board, int numBoats) {
		Random rand = new Random();
		int count = 0;

		while (count < numBoats) {
			int row = rand.nextInt(board.length);
			int col = rand.nextInt(board[0].length);

			// Place ship only if the cell is empty
			if (board[row][col] == WATER) {
				board[row][col] = SHIP;
				count++;
			}
		}
	}

	/**
	 * Simulates the machine's move by randomly selecting a cell on the player's
	 * board. Marks the board with 'X' if a ship is hit, 'O' if missed.
	 *
	 * @param playerBoard The board where the machine attacks.
	 * @return true if a ship is hit false otherwise.
	 */
	public static boolean machineMove() {
		Random rand = new Random();
		int row;
		int col;
		boolean res = false;

		do {
			row = rand.nextInt(playerBoard2.length);
			col = rand.nextInt(playerBoard2[0].length);
		} while (playerBoard2[row][col] == HIT || playerBoard2[row][col] == MISS); // Avoid attacking the same place

		if (playerBoard2[row][col] == SHIP) {
			playerBoard2[row][col] = HIT;
			res = true; // Ship hit
		} else {
			playerBoard2[row][col] = MISS;
		}
		
		return res;
		
	}
}
