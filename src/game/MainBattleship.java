package game;

import java.util.Arrays;

public class MainBattleship {

	public static void main(String[] args) {
		
		char[][] table = new char[10][10];
		
		Battleship.tablePaint(table);
		
		
	}

	
	public boolean placeShips(char tablero[][], char row, int col) {
		boolean done = false;
		int rowNum = row - 'A';
		if (tablero[rowNum][col-1] != 'B') {
			tablero[rowNum][col-1] = 'B';
			done = true;
		}
		return done;
	}
	
	public char[][] initializeBoar(int rows, int cols) {
		char t[][] = new char[rows][cols];
		for (int i = 0; i < t.length; i++) {
			Arrays.fill(t[i], '-');
		}
		return t;
	}

}
