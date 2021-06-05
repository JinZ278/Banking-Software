package banking;

public class WithdrawProcessor {

    protected Bank bank;

    public WithdrawProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processWithdraw(String[] info) {
        String accountId = info[1];
        double amountToStore = Double.parseDouble(info[2]);
        this.bank.getAccounts().get(accountId).withdraw(amountToStore);
    }

}
