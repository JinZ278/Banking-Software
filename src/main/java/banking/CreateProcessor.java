package banking;

public class CreateProcessor {

    private Bank bank;

    public CreateProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCreate(String[] info) {
        Accounts newAccount = null;
        double apr = Double.parseDouble(info[3]);

        if (info[1].toLowerCase().equals("checking")) {
            newAccount = Accounts.checking(apr);
        }
        if (info[1].toLowerCase().equals("savings")) {
            newAccount = Accounts.savings(apr);
        }
        if (info[1].toLowerCase().equals("cd")) {
            newAccount = Accounts.cd(apr, Double.parseDouble(info[4]));
        }
        this.bank.addAccount(info[2], newAccount);
    }
}
