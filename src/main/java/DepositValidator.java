import static java.lang.Integer.parseInt;

public class DepositValidator {

    protected String mainCommand;
    protected String accountId;
    protected String value;
    protected boolean validation;

    Accounts checking = Accounts.checking(1);
    Accounts savings = Accounts.savings(1);
    Accounts cd = Accounts.cd(1, 1000);

    DepositValidator() {
        this.mainCommand = "";
        this.accountId = "";
        this.value = "";
        this.validation = true;
    }

    public boolean validate(String string, Bank bank) {
        stringSplitter(string);
        emptyInputs();
        idCheck(bank);
        accountCheck(bank);
        return this.validation;
    }

    private void stringSplitter(String string) {
        String[] arrOfStr = string.split(" ");
        if (arrOfStr[0].toLowerCase().equals("deposit")) {
            this.mainCommand = arrOfStr[0].toLowerCase();
            stringAssignerDeposit(arrOfStr);
        }
    }

    private void emptyInputs() {
        if (this.mainCommand.equals("") || this.accountId.equals("") || this.value.equals("")) {
            this.validation = false;
        }
    }

    private void idCheck(Bank bank) {
        idHasNoCharacters();
        idWithinLimits();
        idExists(bank);
    }

    private void accountCheck(Bank bank) {
        Accounts account = bank.getAccounts().get(this.accountId);
        if (bank.getAccounts().get(this.accountId) != null) {
            determineAccountClass(account);
        }
    }

    public void stringAssignerDeposit(String[] arrOfStr) {
        if (arrOfStr.length == 3) {
            this.accountId = arrOfStr[1];
            this.value = arrOfStr[2];
        }
    }

    public void idHasNoCharacters() {
        try {
            parseInt(this.accountId);
        } catch (NumberFormatException e) {
            this.accountId = "0";
            this.validation = false;
        }
    }

    public void idWithinLimits() {
        if (parseInt(this.accountId) > 99999999) {
            this.validation = false;
        }
        if (parseInt(this.accountId) < 0) {
            this.validation = false;
        }
    }

    public void idExists(Bank bank) {
        try {
            if (bank.getAccounts().get(this.accountId) == null) {
                this.validation = false;
            }
        } catch (Exception e) {
            this.validation = false;
        }
    }

    public void determineAccountClass(Accounts account) {
        if (account.getClass() == checking.getClass()) {
            valueCheck(0, 1000);
        }
        if (account.getClass() == savings.getClass()) {
            valueCheck(0, 2500);
        }
        if (account.getClass() == cd.getClass()) {
            this.validation = false;
        }
    }

    public void valueCheck(int min, int max) {
        if (Double.parseDouble(this.value) < min) {
            this.validation = false;
        }
        if (Double.parseDouble(this.value) > max) {
            this.validation = false;
        }
    }

}
