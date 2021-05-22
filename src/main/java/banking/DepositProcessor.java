package banking;

public class DepositProcessor {

    private final Bank bank;

    public DepositProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processDeposit(String[] info) {
        String accountId = info[1];
        double amountToStore = Double.parseDouble(info[2]);
        this.bank.getAccounts().get(accountId).deposit(amountToStore);
    }
}
