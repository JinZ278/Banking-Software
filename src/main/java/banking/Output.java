package banking;

import java.util.ArrayList;
import java.util.Map;

public class Output {

    protected Bank bank;

    Output(Bank bank) {
        this.bank = bank;
    }

    public ArrayList<String> processOutput() {

        ArrayList<String> output = new ArrayList<>();
        Map<String, Accounts> accounts = this.bank.getAccounts();
        if (accounts.isEmpty()) {
            return output;
        } else {
            accounts.forEach((key, account) -> outputHelper(output, account, key));
            return output;
        }

    }

    public ArrayList<String> outputHelper(ArrayList<String> list, Accounts accounts, String key) {
        list.add(accounts.getCurrentState(key));
        if (accounts.history != null) {
            accounts.history.forEach((string) -> list.add(string));
        }
        return list;
    }


}
