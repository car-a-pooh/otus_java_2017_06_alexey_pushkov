import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by carapooh on 24.07.2017.
 */
public class ATMDepartment {
    private List<CashHolder> net;
    
    public ATMDepartment(){
        net = new ArrayList<>();
    }
    
    public ATMDepartment(List<CashHolder> net){
        this.net = net;
    }

    public void addCashHolder(CashHolder cashHolder){
        if (cashHolder != null){
            net.add(cashHolder);
        }
    }

    public int getTotalBalance(){
        int balance = 0;
        for (CashHolder holder : net){
            balance += holder.getBalance();
        }
        return balance;
    }
    
    public void doEncashment(){
        for (CashHolder holder : net){
            holder.goToInitialState();
        }
    }
}
