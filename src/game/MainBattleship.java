package game;

import java.util.Scanner;

public class MainBattleship {
	
	static Scanner sc = new Scanner(System.in);
	
	static char pos1 = ' ';
	
	static char pos2 = ' ';

	public static void main(String[] args) {
		
		Battleship game = new Battleship();
		
		int contInicial = 0;
		
		int contJugador = 0;
		int contMaquina = 0;
		
		int turno = 1;
		
		System.out.println("Wellcome to Battleship Game");
		
		System.out.println("First! place your ships in the table (you have 4 ships)");
		
		do {
			
			System.out.println("This is your table:");
			/*MuestraTablero()*/
			
			System.out.println("Where you place a ship?:");
			questionCords();
			
			if(/*Comprobamos coordenadas()*/){
				
				if(/*Colocar()*/) {
					++cont;
				}
			}
			
		} while(cont != 4);
		
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
		System.out.println("Y: ");
		pos2 = sc.next().charAt(0);
	}

}
