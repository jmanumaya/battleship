package game;

import java.util.Arrays;
import java.util.Random;

/**
 * Class BattleShipGame that has attributes that are the characters to place on
 * the board and 4 boards for the game
 */
public class BattleShipGame {
	/**
	 * Attribute indicating water with a line
	 */
	private static char water = '-';
	/**
	 * Attribute indicating ship with a B
	 */
	private static char ship = 'B';
	/**
	 * Attribute indicating that the shot was fired at water
	 */
	private static char hitWater = 'X';
	/**
	 * Attribute indicating that the shot was fired at ship
	 */
	private static char hitShip = 'O';
	/**
	 * Attribute indicating the letter A
	 */
	private static char letterA = 'A';
	/**
	 * Attribute indicating a row
	 */
	private int row;
	/**
	 * Attribute indicating a column
	 */
	private int column;
	
	/**
	 * Constant that indicates the number of ships to be placed
	 */
	private static final int NUM_SHIPS = 4;

	/**
	 * We create the Random
	 */
	private Random rand = new Random();

	/**
	 * Board where the machine will place its ships
	 */
	private char machineTableShips[][] = new char[10][10];
	/**
	 * Board where the machine will shoot
	 */
	private char machineTable[][] = new char[10][10];
	/**
	 * Board where the player will place its ships
	 */
	private char playerTableShips[][] = new char[10][10];
	/**
	 * Board where the player will shoot
	 */
	private char playerTable[][] = new char[10][10];

	/**
	 * MachineTableShips get method that returns its value
	 * 
	 * @return The value of MachineTableShips of BattleShipGame
	 */
	public char[][] getMachineTableShips() {
		return machineTableShips;
	}

	/**
	 * MachineTable get method that returns its value
	 * 
	 * @return The value of MachineTable of BattleShipGame
	 */
	public char[][] getMachineTable() {
		return machineTable;
	}

	/**
	 * PlayerTableShips get method that returns its value
	 * 
	 * @return The value of PlayerTableShips of BattleShipGame
	 */
	public char[][] getPlayerTableShips() {
		return playerTableShips;
	}

	/**
	 * PlayerTable get method that returns its value
	 * 
	 * @return The value of PlayerTable of BattleShipGame
	 */
	public char[][] getPlayerTable() {
		return playerTable;
	}

	/**
	 * Function to initialize the board with scripts
	 * 
	 * @param table The table you are going to receive and fill out
	 */
	public void initializeBoard(char[][] table) {
		for (int i = 0; i < table.length; i++) {
			Arrays.fill(table[i], water);
		}
	}

	/**
	 * Function that prints the board it receives
	 * 
	 * @param table The board that will be printed by console
	 */
	public void tablePaint(char[][] table) {

		// Print column numbers
		for (int i = 1; i <= table.length; i++) {
			System.out.print("\t" + i);
		}
		System.out.println();

		// Print rows with letter labels
		for (int i = 0; i < table.length; i++) {
			System.out.print((char) (letterA + i) + "\t");

			for (int j = 0; j < table[i].length; j++) {
				System.out.print(table[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * Function to check that there are no adjacent ships in the position we want to
	 * place a ship, if there is no ship around, it is placed on the board.
	 * 
	 * @param rowShip    The row where you want to place the boat
	 * @param columnShip The column where you want to place the boat
	 * @param table      The table where we want to place the boat
	 * @return If there are no adjacent ships in the position it returns true,
	 *         otherwise false
	 */
	private boolean putAndCheckShip(int row, int column, char table[][]) {
		boolean placed = true;

		// Check if position is within bounds
        if (row < 0 || row >= table.length || column < 0 || column >= table[0].length) {
            return false;
        }
        
        // Check if there's already a ship at this position
        if (table[row][column] == ship) {
        	placed = false;
        }
        
        // Check all adjacent cells for ships (including diagonals)
        for (int i = Math.max(0, row - 1); i <= Math.min(table.length - 1, row + 1); i++) {
            for (int j = Math.max(0, column - 1); j <= Math.min(table[0].length - 1, column + 1); j++) {
                if (table[i][j] == ship) {
                	placed = false;
                }
            }
        }
			// If the ship can be placed, it is placed on the board
			if (placed) {
				table[row][column] = ship;
			}
		
		return placed;
	}

	/**
	 * Function that receives the row and column where the player wants to place a
	 * ship. We correct the column and row values ​​and return the value of the
	 * checkPosition function.
	 * 
	 * @param rowShip    The row where the user wants to place the ship
	 * @param columnShip The column where the user wants to place the ship
	 * @param table      The table where the user wants to place the ship
	 * @return If the ship could be placed, it returns true, otherwise false
	 */
	public boolean movePlayerShips(char rowShip, int columnShip, char[][] table) {
		correctPositions(rowShip, columnShip);
		return putAndCheckShip(row, column, table);
	}

	/**
	 * Function that receives the row and column as indicated by the tablePaint and
	 * the function gets the correct values ​​for the table
	 * 
	 * @param rowShip    The row as a char
	 * @param columnShip The column received from the user
	 */
	private void correctPositions(char rowShip, int columnShip) {
		row = rowShip - letterA;
		column = columnShip - 1;
	}

	/**
	 * Function that automatically places the 4 ships of the machine on your board
	 * and checks the positions
	 */
	public void moveMachineShips() {
		// The account of the ships placed by the machine
		int count = 0;

		// While it does not exit the loop until all 4 ships are placed on the board
		while (count < NUM_SHIPS) {
			// We generate a random value from 0 to the length of the board for the row and
			// another value for the column
			row = rand.nextInt(0, machineTable.length);
			column = rand.nextInt(0, machineTable.length);
			// If the position is correct, one more is added to the counter.
			if (putAndCheckShip(row, column, machineTableShips)) {
				count++;
			}
		}
	}

	/**
	 * Function that checks whether the user's coordinates have hit a ship on the
	 * machine's board or not
	 * 
	 * @param rowShip    The row indicated by the user
	 * @param columnShip The column indicated by the user
	 * @return If it has hit a ship it returns true, otherwise false
	 */
	public boolean hitShipTrunPlayer(char rowShip, int columnShip) {
		boolean hit = false;
		// We get the row and column with the correct values ​​for the table
		correctPositions(rowShip, columnShip);
		if (machineTableShips[row][column] == ship) {
			if (playerTable[row][column] != hitShip) {
				playerTable[row][column] = hitShip;
				hit = true;
			}
		} else {
			playerTable[row][column] = hitWater;
		}

		return hit;
	}

	/**
	 * Function that generates two random values ​​for the row and column and checks
	 * if the coordinates have given a ship on the user's board
	 * 
	 * @return If it has hit a ship it returns true, otherwise false
	 */
	public boolean hitShipTrunMachine() {
		boolean hit = false;
		// We generate a random value from 0 to the length of the board for the row and
		// another value for the column
		int row = rand.nextInt(0, machineTable.length);
		int column = rand.nextInt(0, machineTable.length);

		if (playerTableShips[row][column] == ship) {
			if (machineTable[row][column] != hitShip) {
				machineTable[row][column] = hitShip;
				hit = true;
			}
		} else {
			machineTable[row][column] = hitWater;
		}

		return hit;
	}
}
