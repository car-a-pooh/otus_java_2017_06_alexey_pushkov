import enums.Banknote;
import exceptions.AmountUnavailableException;

import java.util.HashMap;

/**
 * Created by carapooh on 23.07.2017.
 */
public abstract class CashDispenser implements CashHolderMediator {
    protected HashMap<Banknote, Integer> banknoteCells = new HashMap<>();
    private HashMap<Banknote, Integer> initialLoad = new HashMap<>();
    protected int maxLoad;

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

    public abstract HashMap<Banknote, Integer> withdraw(int amount) throws AmountUnavailableException;
}
