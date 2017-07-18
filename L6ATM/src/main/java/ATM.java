import enums.Banknote;
import exceptions.AmountUnavailableException;
import exceptions.NoEnoughMoneyException;
import exceptions.NoMoreSpaceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by carapooh on 17.07.2017.
 */
public enum ATM {

    INSTANCE;

    public final int INITIAL_LOAD = 100;
    private final int CAPACITY = 500;
    private HashMap<Banknote, Integer> banknoteMap = new HashMap<>();

    ATM(){
        for(Banknote banknote : Banknote.values()){
            banknoteMap.put(banknote, INITIAL_LOAD);
        }
    }

    /**
     * Increments amount of banknotes with given one.
     * @param banknote A banknote to add.
     */
    public void putBanknote(Banknote banknote) throws NoMoreSpaceException {
        if(banknoteMap.containsKey(banknote)){
            if (banknoteMap.get(banknote) < CAPACITY) {
                banknoteMap.put(banknote, banknoteMap.get(banknote) + 1);
            }
            else {
                throw new NoMoreSpaceException();
            }
        }
        else {
            banknoteMap.put(banknote, 1);
        }
    }

    /**
     * Debets a wad of cash.
     * @param wad A Map Banknote -> Quantity to add.
     */
    public void putWadOfCash(WadOfCash wad) throws NoMoreSpaceException {
        for(Banknote banknote : wad.getWad().keySet()){
            if(banknoteMap.containsKey(banknote)){
                if (banknoteMap.get(banknote) + wad.getWad().get(banknote) <= CAPACITY) {
                    banknoteMap.put(banknote, banknoteMap.get(banknote) + wad.getWad().get(banknote));
                }
                else {
                    throw new NoMoreSpaceException();
                }
            }
            else if (wad.getWad().get(banknote) <= CAPACITY) {
                banknoteMap.put(banknote, wad.getWad().get(banknote));
            }
            else {
                throw new NoMoreSpaceException();
            }
        }
    }

    public Banknote withdrawBanknote(Banknote banknote) throws AmountUnavailableException {
        if (banknoteMap.get(banknote) > 0){
            banknoteMap.put(banknote, banknoteMap.get(banknote) - 1);
            return banknote;
        }
        else {
            throw new AmountUnavailableException();
        }
    }

    /**
     * Provides total balance information.
     * @return Available amount of cash.
     */
    public int getBalance(){
        return new WadOfCash(banknoteMap).getAmount();
    }

    /**
     * Withdraws amount.
     * Should try minimal quantity of banknotes.
     * @param amount Amount to withdraw.
     * @return HashMap Banknote -> Quantity
     */
    public WadOfCash getCash(int amount) throws AmountUnavailableException, NoEnoughMoneyException, NoMoreSpaceException {
        Banknote[] aBanknoteList = (Banknote[]) banknoteMap.keySet().toArray();
        reverseSortBanknoteList(aBanknoteList);
        List<WadOfCash> candidates = new ArrayList<>();
        WadOfCash wadOfCash = new WadOfCash();
        int i = 0;
        while (i < aBanknoteList.length){
            int j = i;
            WadOfCash thinWadOfCash = new WadOfCash();
            while (j < aBanknoteList.length){
                while (wadOfCash.getAmount() < amount && banknoteMap.get(aBanknoteList[j]) > 0) {
                    wadOfCash.addBanknote(withdrawBanknote(aBanknoteList[j]));
                    thinWadOfCash.addBanknote(aBanknoteList[j]);
                }
                if (wadOfCash.getAmount() == amount){
                    candidates.add(new WadOfCash(wadOfCash.getWad()));
                    break;
                }
                else if (wadOfCash.getAmount() > amount){
                    break;
                }
                else {
                    j++;
                }
            }
            putWadOfCash(wadOfCash.getThinWadOfCash(thinWadOfCash));
            putBanknote(wadOfCash.getBanknote(aBanknoteList[i]));
            i++;
        }
        if (candidates == null || candidates.size() == 0){
            throw new AmountUnavailableException();
        }
        else {
            int mostThin = Integer.MAX_VALUE;
            WadOfCash mostThinWad = new WadOfCash();
            for (WadOfCash wad : candidates) {
                if (wad.getQuantity() < mostThin) {
                    mostThin = wad.getQuantity();
                    mostThinWad = wad;
                }
            }
            return mostThinWad;
        }
    }

    /**
     * Sort list of banknotes decrease order.
     * @param banknotes
     */
    public void reverseSortBanknoteList(Banknote... banknotes){
        if (banknotes.length >= 2){
            for (int i = 1; i < banknotes.length; i++){
                int j = i;
                while(j > 0 && banknotes[j].getPar() > banknotes[j - 1].getPar()){
                    Banknote temp = banknotes[j];
                    banknotes[j] = banknotes[j - 1];
                    banknotes[j - 1] = temp;
                    j--;
                }
            }
        }
    }
}

