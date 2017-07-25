import java.util.HashSet;
import java.util.Set;

/**
 * Created by carapooh on 24.07.2017.
 */
public class ATMDepartment {
    private Set<CashHolderMediator> net;
    
    public ATMDepartment(){
        net = new HashSet<>();
    }
    
    public ATMDepartment(Set<CashHolderMediator> net){
        this.net = net;
    }

    public void addCashHolder(CashHolderMediator cashHolder){
        if (cashHolder != null){
            net.add(cashHolder);
        }
    }

    public int getTotalBalance(){
        int balance = 0;
        for (CashHolderMediator holder : net){
            balance += holder.getBalance();
        }
        return balance;
    }
    
    public void doEncashment(){
        for (CashHolderMediator holder : net){
            holder.goToInitialState();
        }
    }
}
