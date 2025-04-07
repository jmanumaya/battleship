package game;

import java.util.Arrays;
import java.util.Scanner;

public class MainBattleship {
	
	static Scanner sc = new Scanner(System.in);
	
	static char pos1 = ' ';
	
	static int pos2;

	public static void main(String[] args) {
		
		Battleship game = new Battleship();
		
		int contInicial = 0;
		
		int contJugador = 0;
		int contMaquina = 0;
		
		int turno = 1;
		
		System.out.println("Wellcome to Battleship Game");
		
		System.out.println("First! place your ships in the table (you have 4 ships)");
		
		game.initializeBoardWater(game.getPlayerBoard1());
		game.initializeBoardWater(game.getMachineBoard1());
		
		do {
			
			System.out.println("This is your table:");
			game.initializeBoardLines(game.getPlayerBoard2());
			game.tablePaint(game.getPlayerBoard1());
			
			System.out.println("Where you place a ship?:");
			questionCords();
			
			if(game.checksPosition(pos1, pos2, game.getPlayerBoard1())){	
				game.placeShips(game.getPlayerBoard1(), pos1, pos2);
				++contInicial;
			} else {
				System.out.println("Invalid chosen location, check that the boat is not close to another one");
			}
			
		} while(contInicial != 4);
		
		// Colocar los de la maquina (funcion que va a rellenar directamente el tablero con los barcos aleatoriamente colocados)
		
		// JUGADOR --> TURNO 1
		
		do {
			
			if(turno == 1) {
				
				System.out.println("Its your turn!");
				
				System.out.println("Where you shoot?:");
				/*MuestraTable()*/
				questionCords();
				
				if(/*DisparaJugador()*/){
					
					System.out.println("Nice!! You have sunk a ship");
					++contJugador;
				} else {
					
					System.out.println("MEH, You shoot water");
					++turno;
				}
				game.tablePaint(game.getPlayerBoard1());
				
			} else {
				
				if(/*JuegaMaquina()*/){
					
					System.out.println("WARNING, Computer has sunk a ship");
					++contMaquina;
				} else {
					System.out.println("WARNING, Computer has sunk a ship");
					--turno;
				}
				
			}
			
		} while(contJugador != 4 || contMaquina != 4);
		

	}
	
	private static void questionCords() {
		System.out.println("X: ");
		pos1 = sc.next().charAt(0);
		--pos1;
		System.out.println("Y: ");
		pos2 = sc.nextInt();
		--pos2;
	}

	
	
	
	

}
