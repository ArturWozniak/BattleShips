package ships.war;

/**
 * Created by AW on 2017-07-09.
 */
public class Field {
    private int xCord;
    private int yCord;
    private State state;

    Field(int x,int y, State state){
        this.xCord = x;
        this.yCord = y;
        this.state = state;
    }
    public void setState(State state){
        this.state = state;
    }
    public State getState(){
        return this.state;
    }

    public char toChar(){
        switch (state){
            case HIT:
                return 'X';
            case SHIP:
                return 'S';
            default:
                return 'O';
        }
    }
}
