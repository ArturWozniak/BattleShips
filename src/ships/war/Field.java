package ships.war;

/**
 * Created by AW on 2017-07-09.
 */
class Field {
    private int xCord;
    private int yCord;
    private State state;
    private Ship shipOnField;

    Field(int x,int y, State state){
        this.xCord = x;
        this.yCord = y;
        this.state = state;
        shipOnField = null;
    }
    Field(int x,int y, State state, Ship ship){
        this.xCord = x;
        this.yCord = y;
        this.state = state;
        shipOnField = ship;
    }

    void setState(State state){
        this.state = state;
    }

    Ship getShip(){
        return shipOnField;
    }

    void sunkShip(){
        if (shipOnField != null)
        shipOnField.decrementDecks();
        this.shipOnField = null;
    }

    State getState(){
        return this.state;
    }

    char toChar(){
        switch (state){
            case HIT:
                return 'X';
            case SHIP:
                return 'S';
            default:
                return 'O';
        }
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }
}
