package ships.war;

import java.util.Scanner;

/**
 * Created by AW on 2017-07-10.
 */
public class Player {
    private Board board;
    private String name;

    Player(String name) {
        board = new Board();
        this.name = name;
    }


    private boolean isCordsRight(int x, int y) {
        if (x < 10 && x >= 0 && y < 10 && y >= 0)
            return true;
        else {
            System.out.println("Wrong COORDINATES!!!");
            return false;
        }
    }

    private int[] getCoordinates(int mode) {
        int row;
        int colNo;
        int orient = 1;
        String guess;
        Scanner scanner;
        do {
            do {
                System.out.println("Give first big letter, then number. Like \"A6\"");
                scanner = new Scanner(System.in);
                guess = scanner.next();
            } while (guess.length() < 2 && guess.length() > 3);
            char col = guess.charAt(0);
            if (guess.length() == 2)
                row = Character.getNumericValue(guess.charAt(1)) - 1;
            else if (Character.getNumericValue(guess.charAt(2)) == 0)
                row = 9;
            else
                row = -1;
            colNo = charToInt(col);
            if (mode == 1) {
                System.out.println("Now give orientation: number 1 = HORIZONTAL, number 2 = VERTICAL");
                orient = scanner.nextInt();
            }
        } while (!isCordsRight(row, colNo));
        return new int[]{row, colNo, orient};
    }

    public void fillBoard() {
        int iterator = 0;
        Ship newShip;
        System.out.println("First place 4-decked ship");
        int[] cords = getCoordinates(1);
        do {
            newShip = new FourDeckedShip(cords[0], cords[1], Orientation.fromNumber(cords[2]));
            try {
                board.placeShip(newShip);
            } catch (CantPlaceShipException ex) {
                System.out.println("Can't place it here!");
                cords = getCoordinates(1);
            }
        } while (board.getField(cords[0], cords[1]).getShip() != newShip);
        board.printBoard();
        System.out.println("Now place 2x 3-decked ship");
        iterator = 2;
        while (iterator != 0) {
            cords = getCoordinates(1);
            do {
                newShip = new ThreeDeckedShip(cords[0], cords[1], Orientation.fromNumber(cords[2]));
                try {
                    board.placeShip(newShip);
                } catch (CantPlaceShipException ex) {
                    System.out.println("Can't place it here!");
                    cords = getCoordinates(1);
                }
            } while (board.getField(cords[0], cords[1]).getShip() != newShip);
            iterator -= 1;
            board.printBoard();
        }
        System.out.println("Now place 3x 2-decked ship");
        iterator = 3;
        while (iterator != 0) {
            cords = getCoordinates(1);
            do {
                newShip = new TwoDeckedShip(cords[0], cords[1], Orientation.fromNumber(cords[2]));
                try {
                    board.placeShip(newShip);
                } catch (CantPlaceShipException ex) {
                    System.out.println("Can't place it here!");
                    cords = getCoordinates(1);
                }
            } while (board.getField(cords[0], cords[1]).getShip() != newShip);
            iterator -= 1;
            board.printBoard();
        }
        board.printBoard();
        System.out.println("Now place 4x 1-decked ship");
        iterator = 4;
        while (iterator != 0) {
            cords = getCoordinates(0);
            do {
                newShip = new OneDeckedShip(cords[0], cords[1]);
                try {
                    board.placeShip(newShip);
                } catch (CantPlaceShipException ex) {
                    System.out.println("Can't place it here!");
                    cords = getCoordinates(0);
                }
            } while (board.getField(cords[0], cords[1]).getShip() != newShip);
            iterator -= 1;
            board.printBoard();
        }
    }

    private int charToInt(char character) {
        if (character == 'J') return 10 - 1;
        else
            return Character.getNumericValue((char) (character - 16)) - 1;
    }

    public void guessShip() {
        System.out.println("Give your guess.");
        boolean success;
        do {
            success = false;
            int[] cords = getCoordinates(0);
            try {
                if (board.getField(cords[0], cords[1]).getState() == State.EMPTY) {
                    System.out.println("You've missed");
                    board.getField(cords[0], cords[1]).setState(State.HIT);
                } else {
                    System.out.println("Got it!");
                    success = true;
                    board.getField(cords[0], cords[1]).setState(State.HIT);
                    Ship hitShip = board.getField(cords[0], cords[1]).getShip();
                    board.getField(cords[0], cords[1]).sunkShip();
                    if (hitShip.getDecks() == 0) {
                        board.surroundDestroyedShipWithHITS(hitShip);
                        board.getShipsOnBoard().remove(hitShip);
                        if (board.checkIfBoardIsEmpty()) {
                            System.out.printf("\n\nPLAYER %s HAS LOST \n@@@@@@@@@@@@@@@@@\n\n", this.name);
                            System.exit(0);
                        }
                    }
                    System.out.println("You have another guess!");
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Error guessing");
            }
            board.printBoard();
        } while (success == true);
    }

    public String getName() {
        return name;
    }
}
