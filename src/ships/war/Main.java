package ships.war;

import java.util.Scanner;

/**
 * Created by AW on 2017-07-09.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BattleShips version 1\n");
        System.out.print("FIRST PLAYER NAME: > ");
        String name = scanner.nextLine();
        Player player1 = new Player(name);
        System.out.print("\nSECOND PLAYER NAME: > ");
        name = scanner.nextLine();
        Player player2 = new Player(name);
        System.out.println(player1.getName() + " FILL THE BOARD! :\n");
        player1.fillBoard();
        System.out.println(player2.getName() + " FILL THE BOARD! :\n");
        player2.fillBoard();
       do {
           System.out.println(player1.getName() + " TURN ");
           player2.guessShip();
           System.out.println(player2.getName() + " TURN ");
           player1.guessShip();
       } while(true);
    }
}
