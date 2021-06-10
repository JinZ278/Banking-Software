package banking;

public class TransferProcessor {

    protected Bank bank;

    TransferProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processTransfer(String[] info) {
        double value = Double.parseDouble(info[3]);
        this.bank.getAccounts().get(info[1]).withdraw(value);
        this.bank.getAccounts().get(info[2]).deposit(value);
    }
}
