package ships.war;

/**
 * Created by AW on 2017-07-09.
 */
enum Orientation {
    HOR, VER;

    public static Orientation fromNumber(int x){
        switch (x){
            case 1:
                return HOR;
            default:
                return VER;
        }
    }
}
