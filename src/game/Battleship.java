package game;

import java.util.Arrays;
import java.util.Random;

public class Battleship {

    // Constants for board elements
    private static final char WATER = '~';
    private static final char SHIP = 'S';
    private static final char HIT = 'X';
    private static final char MISS = 'W';

    // Game boards for player and machine
    private static char machineBoard1[][] = new char[10][10];  // Board with ships and water
    private static char machineBoard2[][] = new char[10][10];  // Player's view when playing (shooting)

    private static char playerBoard1[][] = new char[10][10];  // Board with ships and water
    private static char playerBoard2[][] = new char[10][10];  // Machine's view when playing (shooting)

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
        System.out.print("\t");
        for (int i = 1; i <= table[0].length; i++) {
            System.out.print(i + "\t");
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
     * Also ensures ships are not placed in adjacent cells.
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

            // Convert row index to character to use checksPosition method
            char rowChar = (char)('A' + row);
            
            // Check if position is valid (not adjacent to another ship)
            if (checksPosition(rowChar, col + 1, board)) {
                board[row][col] = SHIP;
                count++;
            }
        }
    }

    /**
     * Simulates the machine's move by randomly selecting a cell on the player's
     * board. Marks the board with 'X' if a ship is hit, 'O' if missed.
     *
     * @return true if a ship is hit, false otherwise.
     */
    public static boolean machineMove() {
        Random rand = new Random();
        int row;
        int col;
        boolean res = false;

        do {
            row = rand.nextInt(playerBoard1.length);
            col = rand.nextInt(playerBoard1[0].length);
        } while (playerBoard2[row][col] == HIT || playerBoard2[row][col] == MISS); // Avoid attacking the same place

        if (playerBoard1[row][col] == SHIP) {
            // Mark as hit on both boards
            playerBoard2[row][col] = HIT;
            playerBoard1[row][col] = HIT;
            res = true; // Ship hit
            System.out.println("Computer shot at " + (char)('A' + row) + (col + 1) + " and HIT!");
        } else {
            // Mark miss on both boards
            playerBoard2[row][col] = MISS;
            playerBoard1[row][col] = MISS;  // Aquí está la corrección - también marcamos el fallo en playerBoard1
            System.out.println("Computer shot at " + (char)('A' + row) + (col + 1) + " and MISSED!");
        }
        
        return res;
    }
    
    /**
     * Checks if a position is valid for placing a ship.
     * A position is valid if it's within bounds and there are no ships in adjacent cells.
     *
     * @param letter Row letter
     * @param col Column number (1-based)
     * @param table The board to check
     * @return true if position is valid, false otherwise
     */
    public static boolean checksPosition(char letter, int col, char[][] table) {
        int row = letter - 'A';
        col -= 1; // Convert to 0-based indexing

        // Check if position is within bounds
        if (row < 0 || row >= table.length || col < 0 || col >= table[0].length) {
            return false;
        }
        
        // Check if there's already a ship at this position
        if (table[row][col] == SHIP) {
            return false;
        }
        
        // Check all adjacent cells for ships (including diagonals)
        for (int i = Math.max(0, row - 1); i <= Math.min(table.length - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(table[0].length - 1, col + 1); j++) {
                if (table[i][j] == SHIP) {
                    return false;
                }
            }
        }
        
        // Position is valid
        return true;
    }
    
    /**
     * Places a ship at the specified position.
     *
     * @param table The board where the ship will be placed
     * @param rowChar Row character (A, B, C, etc.)
     * @param col Column number (1-based)
     */
    public void placeShips(char[][] table, char rowChar, int col) {
        int row = rowChar - 'A';
        col -= 1; // Convert to 0-based indexing
        
        if (row >= 0 && row < table.length && col >= 0 && col < table[0].length) {
            table[row][col] = SHIP;
        }
    }
    
    /**
     * Processes a player's shot at the machine's board.
     *
     * @param rowChar Row character (A, B, C, etc.)
     * @param col Column number (1-based)
     * @return true if a ship was hit, false otherwise
     */
    public static boolean playerShoot(char rowChar, int col) {
        int row = rowChar - 'A';
        col -= 1; // Convert to 0-based indexing
        
        // Check if position is valid
        if (row < 0 || row >= machineBoard1.length || col < 0 || col >= machineBoard1[0].length) {
            System.out.println("Invalid coordinates. Try again.");
            return false;
        }
        
        // Check if position has already been shot
        if (machineBoard2[row][col] == HIT || machineBoard2[row][col] == MISS) {
            System.out.println("You already shot at this position. Try again.");
            return false;
        }
        
        // Process the shot
        if (machineBoard1[row][col] == SHIP) {
            machineBoard2[row][col] = HIT;
            machineBoard1[row][col] = HIT; // Update the machine's board too
            return true; // Ship hit
        } else {
            machineBoard2[row][col] = MISS;
            machineBoard1[row][col] = MISS; // También actualizar el tablero original con el fallo
            return false; // Miss
        }
    }
    
    /**
     * Checks if all ships on a board have been hit.
     *
     * @param board The board to check
     * @return true if all ships have been hit, false otherwise
     */
    public static boolean allShipsHit(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == SHIP) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Displays all game boards at the end of the game.
     */
    public static void showAllBoards() {
        System.out.println("\n========= PLAYER'S FLEET BOARD =========");
        tablePaint(playerBoard1);
        
        System.out.println("\n========= PLAYER'S SHOTS BOARD =========");
        tablePaint(machineBoard2);
        
        System.out.println("\n========= COMPUTER'S FLEET BOARD =========");
        tablePaint(machineBoard1);
        
        System.out.println("\n========= COMPUTER'S SHOTS BOARD =========");
        tablePaint(playerBoard2);
    }
}