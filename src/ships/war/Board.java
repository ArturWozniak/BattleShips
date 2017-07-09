package ships.war;

/**
 * Created by AW on 2017-07-09.
 */
public class Board {
    private final int SIZE = 10;
    private Field[][] board;

    Board() {
        board = new Field[SIZE][SIZE];
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
    }

    public Field getField(int x, int y) {
        return board[x][y];
    }

    public void setField(int x, int y, State state) {
        board[x][y] = new Field(x, y, state);
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
                setField(x,y,State.SHIP);
                y += 1;
            }
            else {
                setField(x,y,State.SHIP);
                x += 1;
            }
        }
    }
}