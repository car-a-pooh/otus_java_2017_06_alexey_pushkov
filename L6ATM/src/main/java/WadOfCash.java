import enums.Banknote;
import exceptions.AmountUnavailableException;
import exceptions.NoEnoughMoneyException;

import java.util.HashMap;

/**
 * Created by carapooh on 18.07.2017.
 */
public class WadOfCash {
    private HashMap<Banknote, Integer> wad;

    public WadOfCash(){
        wad = new HashMap<Banknote, Integer>();
    }

    public WadOfCash(HashMap<Banknote, Integer> wad){
        this.wad = new HashMap<Banknote, Integer>();
        this.wad.putAll(wad);
    }

    public HashMap<Banknote, Integer> getWad(){
        return wad;
    }

    public int getQuantity(){
        int q = 0;
        for (Banknote banknote : wad.keySet()){
            q += wad.get(banknote);
        }
        return q;
    }

    /**
     * Counts amount of wad of cash.
     * @return
     */
    public int getAmount(){
        int amount = 0;
        for (Banknote banknote : wad.keySet()){
            amount += banknote.getPar() * wad.get(banknote);
        }
        return amount;
    }

    public void addBanknote(Banknote banknote){
        if (wad.containsKey(banknote)){
            wad.put(banknote, wad.get(banknote) + 1);
        }
        else {
            wad.put(banknote, 1);
        }
    }

    public Banknote getBanknote(Banknote banknote) throws AmountUnavailableException {
        if (wad.get(banknote) > 0){
            wad.put(banknote, wad.get(banknote) - 1);
            return banknote;
        }
        else {
            throw new AmountUnavailableException();
        }
    }

    public WadOfCash getThinWadOfCash(WadOfCash thinWadOfCash) throws AmountUnavailableException, NoEnoughMoneyException {
        if (thinWadOfCash.getAmount() > this.getAmount()){
            throw new NoEnoughMoneyException();
        }
        else {
            for (Banknote banknote : thinWadOfCash.getWad().keySet()){
                if (!wad.containsKey(banknote) || wad.get(banknote) < thinWadOfCash.getWad().get(banknote)){
                    throw new AmountUnavailableException();
                }
            }
            for (Banknote banknote : thinWadOfCash.getWad().keySet()){
                wad.put(banknote, wad.get(banknote) - thinWadOfCash.getWad().get(banknote));
            }
            return thinWadOfCash;
        }
    }
}
