import enums.Banknote;
import exceptions.AmountUnavailableException;
import exceptions.NoMoreSpaceException;

import java.util.HashMap;

/**
 * Created by carapooh on 24.07.2017.
 */
public class LoadATM extends CashDispenser {

    public LoadATM(){
        super(500);
    }

    public void putBanknote(Banknote banknote) throws NoMoreSpaceException {
        if (banknoteCells.get(banknote) < maxLoad){
            banknoteCells.put(banknote, banknoteCells.get(banknote) + 1);
        }
        else {
            throw new NoMoreSpaceException();
        }
    }

    public HashMap<Banknote, Integer> withdraw(int amount) throws AmountUnavailableException {
        HashMap<Banknote, Integer> candidate = new HashMap<>();
        if (tryToGatherAmount(Banknote.ONE, amount, candidate) == 0){
            for (Banknote banknote : candidate.keySet()){
                banknoteCells.put(banknote, banknoteCells.get(banknote) -
                        candidate.get(banknote));
            }
            return candidate;
        }
        else {
            throw new AmountUnavailableException();
        }
    }

    private int tryToGatherAmount(Banknote banknote, int amount,
                                      HashMap<Banknote, Integer> candidate){
        int counter = 0;
        while (counter <= banknoteCells.get(banknote) && amount > 0) {
            int sum = -1;

            while (banknote.hasNext() && (sum = tryToGatherAmount(banknote.getNext(), amount, candidate)) > 0);

            amount -= banknote.getPar();
            if (sum == 0) {
                candidate.put(banknote, counter);
                return 0;
            }
            if (amount == 0){
                candidate.put(banknote, counter + 1);
                return 0;
            }
            counter++;
        }
        return amount;
    }

}
