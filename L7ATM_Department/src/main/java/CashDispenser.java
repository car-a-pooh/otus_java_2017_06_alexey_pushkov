import enums.Banknote;
import exceptions.AmountUnavailableException;
import exceptions.NoMoreSpaceException;

import java.util.HashMap;

/**
 * Created by carapooh on 23.07.2017.
 */
public abstract class CashDispenser implements CashHolder {
    private HashMap<Banknote, Integer> banknoteCells = new HashMap<>();
    private HashMap<Banknote, Integer> initialLoad = new HashMap<>();
    private int maxLoad;

    public CashDispenser(int maxLoad){
        this.maxLoad = maxLoad;
        for (Banknote banknote : Banknote.values()){
            initialLoad.put(banknote, maxLoad);
            banknoteCells.put(banknote, maxLoad);
        }
    }

    public CashDispenser(HashMap<Banknote, Integer> wadOfCash, int maxLoad){
        this(maxLoad);
        for (Banknote banknote : wadOfCash.keySet()){
            initialLoad.put(banknote, Math.min(wadOfCash.get(banknote), maxLoad));
            banknoteCells.put(banknote, Math.min(wadOfCash.get(banknote), maxLoad));
        }
    }

    protected boolean isFull(Banknote cell){
        return banknoteCells.get(cell) >= maxLoad;
    }

    protected int getCellLoad(Banknote cell){
        return banknoteCells.get(cell);
    }

    protected void setCellLoad(Banknote banknote, int quantity) throws NoMoreSpaceException {
        if (quantity >= 0 && quantity <= maxLoad) {
            banknoteCells.put(banknote, quantity);
        }
        else {
            throw new NoMoreSpaceException();
        }
    }

    public int getBalance(){
        int balance = 0;
        for (Banknote banknote : banknoteCells.keySet()){
            balance += banknoteCells.get(banknote) * banknote.getPar();
        }
        return balance;
    }

    public void goToInitialState(){
        banknoteCells.putAll(initialLoad);
    }

    public abstract HashMap<Banknote, Integer> withdraw(int amount) throws AmountUnavailableException, NoMoreSpaceException;
}
