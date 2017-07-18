package enums;

/**
 * Created by carapooh on 17.07.2017.
 */
public enum Banknote {

    FIFTY,
    TWENTY_FIVE,
    TEN,
    FIVE,
    ONE;

    public int getPar(){
        switch (this){
            case ONE : return 1;
            case FIVE : return 5;
            case TEN : return 10;
            case TWENTY_FIVE : return 25;
            case FIFTY : return 50;
            default : return 0;
        }
    }
}
