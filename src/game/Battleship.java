package game;

import java.util.Arrays;
import java.util.Random;

public class Battleship {

	// Constants for board elements
	private static final char WATER = '-';
	private static final char SHIP = 'S';
	private static final char HIT = 'X';
	private static final char MISS = 'O';

	// Game boards for player and machine
	private static char machineBoard1[][] = new char[10][10];  // COLOCAR BARCOS Y AGUA
	private static char machineBoard2[][] = new char[10][10];  // jugar

	private static char playerBoard1[][] = new char[10][10];  // COLOCAR BARCOS Y AGUA
	private static char playerBoard2[][] = new char[10][10];  // JUGAR


	public static char[][] getMachineBoard1() {
		return machineBoard1;
	}

	public static void setMachineBoard1(char[][] machineBoard1) {
		Battleship.machineBoard1 = machineBoard1;
	}

	public static char[][] getMachineBoard2() {
		return machineBoard2;
	}

	public static void setMachineBoard2(char[][] machineBoard2) {
		Battleship.machineBoard2 = machineBoard2;
	}

	public static char[][] getPlayerBoard1() {
		return playerBoard1;
	}

	public static void setPlayerBoard1(char[][] playerBoard1) {
		Battleship.playerBoard1 = playerBoard1;
	}

	public static char[][] getPlayerBoard2() {
		return playerBoard2;
	}

	public static void setPlayerBoard2(char[][] playerBoard2) {
		Battleship.playerBoard2 = playerBoard2;
	}

	/**
	 * Prints the game board in a structured format.
	 *
	 * @param table The board to print.
	 */
	public static void tablePaint(char[][] table) {

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
	public static void initializeBoardWater(char[][] table) {
		for (int i = 0; i < table.length; i++) {
			Arrays.fill(table[i], WATER);
		}
	}
	
	public static void initializeBoardLines(char[][] table) {
		for (int i = 0; i < table.length; i++) {
			Arrays.fill(table[i], '-');
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
	 * @return true if a ship is hit, false otherwise.
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
	
	public boolean checksPosition(char letter, int posJ, char[][] table) {
		boolean valid = false;
		int posI = letter - 'A';
		posJ -= 1;

		// If the position is between the limits and there isn't a ship on the position,
		// the position is valid
		if ((posI >= 0 && posI < table.length) && (posJ >= 0 && posJ < table[posJ].length)
				&& table[posI][posJ] != 'S') {
			valid = true;
		}

		// We check the adjacent positions

		// Check if the adjacent position is between the limits -> i, j - 1
		if ((posI >= 0 && posI < table.length) && (posJ - 1 >= 0 && posJ - 1 < table[posJ].length)) {
			// Check if there is a ship in the position
			if (table[posI][posJ - 1] == 'S') {
				valid = false;
			}
		}

		// Check if the adjacent position is between the limits -> i, j + 1
		else if ((posI >= 0 && posI < table.length) && (posJ + 1 >= 0 && posJ + 1 < table[posJ].length)) {
			// Check if there is a ship in the position
			if (table[posI][posJ + 1] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i - 1, j
		else if ((posI - 1 >= 0 && posI - 1 < table.length) && (posJ >= 0 && posJ < table[posJ].length)) {
			// Check if there is a ship in the position
			if (table[posI - 1][posJ] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i - 1, j - 1
		else if ((posI - 1 >= 0 && posI - 1 < table.length)
				&& (posJ - 1 >= 0 && posJ - 1 < table[posJ].length)) {
			// Check if there is a ship in the position
			if (table[posI - 1][posJ - 1] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i - 1, j + 1
		else if ((posI - 1 >= 0 && posI - 1 < table.length)
				&& (posJ + 1 >= 0 && posJ + 1 < table[posJ].length)) {
			// Check if there is a ship in the position
			if (table[posI - 1][posJ + 1] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i + 1, j
		else if ((posI + 1 >= 0 && posI + 1 < table.length) && (posJ >= 0 && posJ < table[posJ].length)) {
			// Check if there is a ship in the position
			if (table[posI + 1][posJ] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i + 1, j - 1
		else if ((posI + 1 >= 0 && posI + 1 < table.length)
				&& (posJ - 1 >= 0 && posJ - 1 < table[posJ].length)) {
			// Check if there is a ship in the position
			if (table[posI + 1][posJ - 1] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i + 1, j + 1
		else if ((posI + 1 >= 0 && posI + 1 < table.length)
				&& (posJ + 1 >= 0 && posJ + 1 < table[posJ].length)) {
			// Check if there is a ship in the position
			if (table[posI + 1][posJ + 1] == 'S') {
				valid = false;
			}
		}
		
		if (table[posI][posJ] != 'S') {
			valid = true;
		}

		// Return boolean true or false
		return valid;
		
			/* boolean valid = true;
	        
	        // Check if position is within bounds
	        if (posI >= 0 && posI < tablePlayer.length && 
	            posJ >= 0 && posJ < tablePlayer[0].length) {
	            
	            // Check current position
	            if (tablePlayer[posI][posJ] == 'S') {
	                valid = false;
	            }
	            
	            // Check adjacent positions
	            for (int i = Math.max(0, posI - 1); i <= Math.min(tablePlayer.length - 1, posI + 1) && valid; 
	                 i++) {
	                for (int j = Math.max(0, posJ - 1); 
	                     j <= Math.min(tablePlayer[0].length - 1, posJ + 1) && valid; 
	                     j++) {
	                    if (tablePlayer[i][j] == 'S') {
	                        valid = false;
	                    }
	                }
	            }
	        } else {
	            valid = false;
	        }
	        
	        return valid; */
	}
	
	public void placeShips(char table[][], char row, int col) {
		table[row][col] = SHIP;
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
