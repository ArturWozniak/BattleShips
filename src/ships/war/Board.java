package ships.war;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by AW on 2017-07-09.
 */
public class Board {
    private final int SIZE = 10;
    private Field[][] board;
    private HashSet<Ship> shipsOnBoard;

    Board() {
        board = new Field[SIZE][SIZE];
        shipsOnBoard = new HashSet<>();
        initializeBoard();
        printBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Field(i, j, State.EMPTY);
            }
        }
    }

    private void printLetters() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((char) ('A' + i));
            System.out.print(' ');
        }
    }

    public void printBoard() {
        System.out.printf("\n\n");
        printLetters();
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("\n%d", i + 1);
            if (i < 9) {
                System.out.print(' ');
            }
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j].toChar());
                System.out.print(' ');
            }
        }
        System.out.println("");
    }

    public Field getField(int x, int y){
        try {
            return board[x][y];
        }
        catch (ArrayIndexOutOfBoundsException ex){
            return null;
        }
    }

    private void setField(int x, int y, State state,Ship ship) {
        board[x][y] = new Field(x, y, state,ship);
    }

    private boolean isEmptySurrounding(int x, int y) throws CantPlaceShipException {
        try {
            if (board[x][y].getState() != State.EMPTY)
                return false;
        } catch (IndexOutOfBoundsException ex) {
            throw new CantPlaceShipException();
        }
        try {
            if (board[x + 1][y + 1].getState() == State.SHIP)
                return false;
            if (board[x + 1][y].getState() == State.SHIP)
                return false;
            if (board[x + 1][y - 1].getState() == State.SHIP)
                return false;
            if (board[x][y + 1].getState() == State.SHIP)
                return false;
            if (board[x][y - 1].getState() == State.SHIP)
                return false;
            if (board[x - 1][y].getState() == State.SHIP)
                return false;
            if (board[x - 1][y + 1].getState() == State.SHIP)
                return false;
            if (board[x - 1][y - 1].getState() == State.SHIP)
                return false;
        } catch (IndexOutOfBoundsException ex) {
            //nothing bad happend
        }
        return true;
    }

    public void placeShip(Ship ship) throws CantPlaceShipException, IndexOutOfBoundsException {
        int x = ship.getxCord();
        int y = ship.getyCord();
        Orientation orient = ship.getOrientation();
        int noDecks = ship.getDecks();
        int iterator = noDecks;
        while (iterator != 0) {
            if (orient == Orientation.HOR) {
                if (!isEmptySurrounding(x, y)) {
                    throw new CantPlaceShipException();
                }
                y += 1;
            }
            else{
                if (!isEmptySurrounding(x, y)) {
                    throw new CantPlaceShipException();
                }
                x += 1;
            }
            iterator -= 1;
        }
        x = ship.getxCord();
        y = ship.getyCord();
        for (iterator = 0; iterator < noDecks; iterator++) {
            if (orient == Orientation.HOR) {
                setField(x,y,State.SHIP,ship);
                ship.addOccupied(board[x][y]);
                shipsOnBoard.add(ship);
                y += 1;
            }
            else {
                setField(x,y,State.SHIP,ship);
                ship.addOccupied(board[x][y]);
                shipsOnBoard.add(ship);
                x += 1;
            }
        }
    }

    public void surroundDestroyedShipWithHITS(Ship ship){
        for (Field field: ship.getOccupied()
             ) {
            int x = field.getxCord();
            int y = field.getyCord();
            try {board[x+1][y-1].setState(State.HIT);}
            catch (Exception ex) {//nothing bad happened}
            }
            try {board[x+1][y].setState(State.HIT);}
            catch (Exception ex) {//nothing bad happened}
            }
            try {board[x+1][y+1].setState(State.HIT);}
            catch (Exception ex) {//nothing bad happened}
            }
            try {board[x][y-1].setState(State.HIT);}
            catch (Exception ex) {//nothing bad happened}
            }
            try {board[x][y+1].setState(State.HIT);}
            catch (Exception ex) {//nothing bad happened}
            }
            try {board[x-1][y-1].setState(State.HIT);}
            catch (Exception ex) {//nothing bad happened}
            }
            try {board[x-1][y].setState(State.HIT);}
            catch (Exception ex) {//nothing bad happened}
            }
            try {board[x-1][y+1].setState(State.HIT);}
            catch (Exception ex) {//nothing bad happened}
            }
        }
    }
    public boolean checkIfBoardIsEmpty(){
       return shipsOnBoard.isEmpty();
    }

    public HashSet<Ship> getShipsOnBoard() {
        return shipsOnBoard;
    }
}
