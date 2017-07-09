package ships.war;

/**
 * Created by AW on 2017-07-09.
 */
public class CantPlaceShipException extends Exception{
    CantPlaceShipException(){
        super("There is no space");
    }
}
