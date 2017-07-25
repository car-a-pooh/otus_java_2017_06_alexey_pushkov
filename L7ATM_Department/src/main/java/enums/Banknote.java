package enums;

/**
 * Created by carapooh on 17.07.2017.
 */
public enum Banknote {

    HUNDRED,
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
            case HUNDRED : return 100;
            default : return 0;
        }
    }

    public boolean hasNext(){
        return !this.equals(HUNDRED);
    }

    public Banknote getNext(){
        switch (this){
            case ONE : return FIVE;
            case FIVE : return TEN;
            case TEN : return TWENTY_FIVE;
            case TWENTY_FIVE : return FIFTY;
            case FIFTY : return HUNDRED;
            default : throw new IllegalArgumentException();
        }
    }
}
