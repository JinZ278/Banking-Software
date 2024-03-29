package banking;

import java.util.ArrayList;
import java.util.Map;

public class PassProcessor {
    protected Bank bank;
    private ArrayList<String> list;

    public PassProcessor(Bank bank) {
        this.bank = bank;
        this.list = new ArrayList<>();
    }

    public void processPass(int months) {
        Map<String, Accounts> accounts = this.bank.getAccounts();
        accounts.forEach((key, account) -> account.age += months);

        for (int i = 0; i < months; i++) {
            accounts.forEach((key, account) -> zeroBalanceCheck(key, account));
            removeAllZeroBalanceAccounts();
            accounts.forEach((key, account) -> minimumBalanceFee(account));
            accounts.forEach((key, account) -> account.aprCalculate());
            accounts.forEach((key, account) -> account.withdrawn = false);
        }
    }

    public void zeroBalanceCheck(String key, Accounts account) {
        if (account.balance == 0) {
            this.list.add(key);
        }
    }

    private void removeAllZeroBalanceAccounts() {
        this.list.forEach((key) -> this.bank.getAccounts().remove(key));
        this.list = new ArrayList<>();
    }

    public void minimumBalanceFee(Accounts account) {
        if (account.getBalance() < 100) {
            account.balance -= 25;
        }
    }
}
