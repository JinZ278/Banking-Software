public class CommandProcessor {

    public void process(String valid_create_string, Bank bank) {
        String[] info = stringSplitter(valid_create_string);
        if (info[0].toLowerCase().equals("create")) {
            createProcess(info, bank);
        }
        if (info[0].toLowerCase().equals("deposit")) {
            depositProcess(info, bank);
        }
    }

    private void createProcess(String[] info, Bank bank) {
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
        bank.addAccount(info[2], newAccount);
    }

    private void depositProcess(String[] info, Bank bank) {
    }

    private String[] stringSplitter(String newString) {
        String[] newStringSplitIntoArray = newString.split(" ");
        return newStringSplitIntoArray;
    }
}
