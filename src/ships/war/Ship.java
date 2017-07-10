package ships.war;

import java.util.ArrayList;

/**
 * Created by AW on 2017-07-09.
 */

public abstract class Ship {

    private int xCord;
    private int yCord;
    private int decksNo;
    private boolean isSunk;
    private Orientation orientation;
    private ArrayList<Field> occupied;

    Ship(int x, int y, int numberOfDecks, Orientation orientation) {
        xCord = x;
        yCord = y;
        this.decksNo = numberOfDecks;
        this.orientation = orientation;
        this.occupied = new ArrayList<>();
    }

    int getxCord() {
        return xCord;
    }

    int getyCord() {
        return yCord;
    }

    Orientation getOrientation(){
        return orientation;
    }

    int getDecks(){
        return decksNo;
    }
    public void decrementDecks(){
        decksNo--;
        if(decksNo == 0){
            System.out.println("You've destroyed this ship!");
        }
    }
    public ArrayList<Field> getOccupied(){
        return occupied;
    }

    public void addOccupied(Field field){
         occupied.add(field);
    }
}
