import enums.Banknote;
import exceptions.AmountUnavailableException;
import exceptions.NoMoreSpaceException;

import java.util.HashMap;

/**
 * Created by carapooh on 24.07.2017.
 */
public class SimpleATM extends CashDispenser {

    public SimpleATM(){
        super(100);
    }
    public HashMap<Banknote, Integer> withdraw(int amount) throws AmountUnavailableException, NoMoreSpaceException {
        HashMap<Banknote, Integer> withdrawalWad = new HashMap<>();
        for (Banknote banknote : Banknote.values()) {
            withdrawalWad.put(banknote, 0);
        }
        for (Banknote banknote : Banknote.values()) {
            int counter = Math.min(getCellLoad(banknote), amount / banknote.getPar());
            withdrawalWad.put(banknote, counter);
            amount -= counter * banknote.getPar();
        }
        if (amount == 0){
            for (Banknote banknote : Banknote.values()){
                setCellLoad(banknote, getCellLoad(banknote) - withdrawalWad.get(banknote));
            }
            return withdrawalWad;
        }
        else {
            throw new AmountUnavailableException();
        }
    }
}
