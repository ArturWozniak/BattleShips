package ships.war;

/**
 * Created by AW on 2017-07-09.
 */
public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Artur");
        player1.fillBoard();
       /*
        Board board = new Board();
        try {
            board.placeShip(new OneDeckedShip(5,5));
        }
        catch (CantPlaceShipException ex){
            System.out.printf("\nYou cant place it there!\n");
        }
        board.printBoard();
        try {
            board.placeShip(new ThreeDeckedShip(7,7,Orientation.HOR));
        }
        catch (CantPlaceShipException ex){
            System.out.printf("\nYou cant place it there!\n");
        }
        board.printBoard();
        try {
            board.placeShip(new FourDeckedShip(2,8,Orientation.VER));
        }
        catch (CantPlaceShipException ex){
            System.out.printf("\nYou cant place it there!\n");
        }
        board.printBoard();
        */
    }
}
