package ships.war;

/**
 * Created by AW on 2017-07-09.
 */

public abstract class Ship {

    private int xCord;
    private int yCord;
    private int decksNo;
    private boolean isSunk;
    private Orientation orientation;

    Ship(int x, int y, int number, Orientation orientation) {
        xCord = x;
        yCord = y;
        this.decksNo = number;
        this.orientation = orientation;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public Orientation getOrientation(){
        return orientation;
    }
    public  int getDecks(){
        return decksNo;
    }
}
