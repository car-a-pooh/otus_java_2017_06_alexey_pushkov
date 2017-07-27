package enums;

/**
 * Created by carapooh on 17.07.2017.
 */
public enum Banknote {

    HUNDRED(100, null),
    FIFTY(50, HUNDRED),
    TWENTY_FIVE(25, FIFTY),
    TEN(10, TWENTY_FIVE),
    FIVE(5, TEN),
    ONE(1, FIVE);

    private final int par;
    private final Banknote next;

    Banknote(int par, Banknote next){
        this.par = par;
        this.next = next;
    }

    public int getPar(){
        return par;
    }

    public boolean hasNext(){
        return next != null;
    }

    public Banknote getNext(){
        return next;
    }
}
