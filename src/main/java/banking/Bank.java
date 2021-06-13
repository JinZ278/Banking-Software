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

    public boolean idCheck(String id) {
        try {
            if (accounts.get(id) == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
