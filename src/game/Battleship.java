package game;

import java.util.Arrays;

<<<<<<< HEAD
public class Battleship {
	
	
	public static void tablePaint(char[][] table) {

		initializeBoar(table);
		
		char letterA = 'A';

		for (int i = 1; i <= table.length; i++) {

			System.out.print("\t" + i);

		}
		
		System.out.println();

		for (int i = 0; i < table.length; i++) {

			System.out.print((char) (letterA + i) + "\t");
			
			
			for (int j = 0; j < table.length; j++) {

				System.out.print(table[i][j] + "\t");
				
				
			}
			
			System.out.println();

		}

	}
	
	public static void initializeBoar(char[][] table) {
		for (int i = 0; i < table.length; i++) {
			Arrays.fill(table[i], '-');
		}
	
	}

=======
	char tablero1Maquina[][] = new char [10][10];
	char tablero2Maquina[][] = new char [10][10];
	
	char tablero1Jugador[][] = new char [10][10];
	char tablero2Jugador[][] = new char [10][10];
	
	
	
>>>>>>> refs/remotes/origin/jmaya
}
