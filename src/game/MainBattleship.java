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

        System.out.println("Welcome to Battleship Game");
        System.out.println("First! Place your ships on the table (you have 4 ships)");

        // Initialize all boards
        game.initializeBoardWater(game.getPlayerBoard1());
        game.initializeBoardWater(game.getPlayerBoard2());
        game.initializeBoardWater(game.getMachineBoard1());
        game.initializeBoardWater(game.getMachineBoard2());

        // Player places their ships
        do {
            System.out.println("This is your table:");
            game.tablePaint(game.getPlayerBoard1());

            System.out.println("Where do you want to place a ship?");
            questionCords();

            if (Battleship.checksPosition(pos1, pos2, game.getPlayerBoard1())) {
                game.placeShips(game.getPlayerBoard1(), pos1, pos2);
                ++contInicial;
                System.out.println("Ship placed successfully! (" + contInicial + "/4)");
            } else {
                System.out.println("Invalid chosen location, check that the boat is not close to another one");
            }

        } while (contInicial != 4);

        // Computer places its ships
        System.out.println("Computer is placing its ships...");
        game.generateBoats(game.getMachineBoard1(), 4);
        System.out.println("Computer has placed all its ships!");

        // Game starts
        System.out.println("\nLet the battle begin!");
        
        boolean gameOver = false;

        do {
            if (turno == 1) {
                // Player's turn
                System.out.println("\n========= YOUR TURN! =========");
                System.out.println("Your fleet:");
                game.tablePaint(game.getPlayerBoard1());
                System.out.println("\nYour shots:");
                game.tablePaint(game.getMachineBoard2());

                System.out.println("Where do you want to shoot?");
                questionCords();

                if (Battleship.playerShoot(pos1, pos2)) {
                    System.out.println("Nice!! You have sunk a ship!");
                    ++contJugador;
                    
                    if (contJugador == 4) {
                        System.out.println("Congratulations! You have sunk all enemy ships!");
                        gameOver = true;
                    }
                } else {
                    System.out.println("You missed! The turn passes to the computer.");
                    turno = 2;
                }

            } else {
                // Computer's turn
                System.out.println("\n========= COMPUTER'S TURN! =========");
                
                if (Battleship.machineMove()) {
                    ++contMaquina;
                    
                    if (contMaquina == 4) {
                        System.out.println("The computer has sunk all your ships!");
                        gameOver = true;
                    }
                } else {
                    System.out.println("Computer missed! Your turn now.");
                    turno = 1;
                }
                
                System.out.println("Your fleet after computer's attack:");
                game.tablePaint(game.getPlayerBoard1());
            }

        } while (!gameOver);

        // Game over, show final state
        System.out.println("\n========= GAME OVER! =========");
        if (contJugador == 4) {
            System.out.println("YOU WIN! You sunk all enemy ships!");
        } else {
            System.out.println("YOU LOSE! The computer sunk all your ships!");
        }
        
        // Show all boards at the end
        Battleship.showAllBoards();
        
        sc.close();
    }

    private static void questionCords() {
        System.out.print("Row (A-J): ");
        pos1 = sc.next().toUpperCase().charAt(0);
        
        System.out.print("Column (1-10): ");
        pos2 = sc.nextInt();
        
        // Validate input
        if (pos1 < 'A' || pos1 > 'J' || pos2 < 1 || pos2 > 10) {
            System.out.println("Invalid coordinates! Row must be A-J and column must be 1-10.");
            questionCords(); // Ask again
        }
    }
}