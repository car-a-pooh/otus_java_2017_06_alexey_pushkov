import enums.Banknote;
import exceptions.AmountUnavailableException;
import exceptions.NoMoreSpaceException;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by carapooh on 25.07.2017.
 */
public class ATMWithdrawTest {

    @Test
    public void test() throws AmountUnavailableException, NoMoreSpaceException {
        Set<CashDispenser> atms = new HashSet<>();
        atms.add(new SimpleATM());
        atms.add(new LoadATM());
        for (CashDispenser cashDispenser : atms) {
            HashMap<Banknote, Integer> wad = cashDispenser.withdraw(90);
            System.out.println(cashDispenser.getClass());
            for (Banknote banknote : wad.keySet()) {
                System.out.println("Banknote " + banknote.getPar() + " : " + wad.get(banknote) + " pieces.");
            }
        }
    }
}
