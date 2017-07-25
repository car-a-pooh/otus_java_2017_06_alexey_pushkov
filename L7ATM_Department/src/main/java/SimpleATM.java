import enums.Banknote;
import exceptions.AmountUnavailableException;

import java.util.HashMap;

/**
 * Created by carapooh on 24.07.2017.
 */
public class SimpleATM extends CashDispenser {

    public SimpleATM(){
        super(100);
    }
    public HashMap<Banknote, Integer> withdraw(int amount) throws AmountUnavailableException {
        HashMap<Banknote, Integer> withdrawalWad = new HashMap<>();
        for (Banknote banknote : banknoteCells.keySet()) {
            withdrawalWad.put(banknote, 0);
        }
        for (Banknote banknote : banknoteCells.keySet()) {
            int counter = Math.min(banknoteCells.get(banknote), amount / banknote.getPar());
            withdrawalWad.put(banknote, counter);
            amount -= counter * banknote.getPar();
        }
        if (amount == 0){
            for (Banknote banknote : withdrawalWad.keySet()){
                banknoteCells.put(banknote, banknoteCells.get(banknote) - withdrawalWad.get(banknote));
            }
            return withdrawalWad;
        }
        else {
            throw new AmountUnavailableException();
        }
    }
}
