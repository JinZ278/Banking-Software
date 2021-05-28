package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Accounts> accounts;

    Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Accounts> getAccounts() {
        return accounts;
    }

    public void addAccount(String quickId, Accounts givenAccount) {
        accounts.put(quickId, givenAccount);
    }

    public void removeAccount(String quickId) {
        accounts.remove(quickId);
    }
}
