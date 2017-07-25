import enums.Banknote;
import exceptions.AmountUnavailableException;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by carapooh on 25.07.2017.
 */
public class ATMDepartmentTest {

    @Test
    public void test() throws AmountUnavailableException {
        Set<CashDispenser> atms = new HashSet<>();
        atms.add(new SimpleATM());
        atms.add(new LoadATM());
        for (CashDispenser cashDispenser : atms) {
            HashMap<Banknote, Integer> wad = cashDispenser.withdraw(23);
        }
        ATMDepartment department = new ATMDepartment();
        for (CashDispenser cashDispenser : atms) {
            department.addCashHolder(cashDispenser);
        }
        System.out.println(department.getTotalBalance());
    }
}
