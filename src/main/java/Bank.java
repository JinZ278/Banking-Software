import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<Integer, Accounts> accounts;

    Bank() {
        accounts = new HashMap<>();
    }

    public Map<Integer, Accounts> getAccounts() {
        return accounts;
    }

    public void addAccount(int quickId, Accounts givenAccount) {
        accounts.put(quickId, givenAccount);
    }
}
