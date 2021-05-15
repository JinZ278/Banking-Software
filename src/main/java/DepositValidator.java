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

    public boolean validate(String commandString, Bank bank) {
        String newString = stringIsSpaces(commandString);
        stringSplitter(newString);
        emptyInputs();
        idCheck(bank);
        accountCheck(bank);
        return this.validation;
    }

    private String stringIsSpaces(String string) {
        if (string.isBlank()) {
            this.validation = false;
            return "0";
        } else {
            return string;
        }
    }

    private void stringSplitter(String newString) {
        String[] newStringSplitInArray = newString.split(" ");
        String firstWord = newStringSplitInArray[0].toLowerCase();

        if (firstWord.equals("deposit")) {
            this.mainCommand = newStringSplitInArray[0].toLowerCase();
            stringAssignerDeposit(newStringSplitInArray);
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
            valueCheck(account);
        }
    }

    public void stringAssignerDeposit(String[] arrOfStr) {
        if (arrOfStr.length == 3) {
            this.accountId = arrOfStr[1];
            this.value = arrOfStr[2];
        } else {
            this.validation = false;
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

    public void valueCheck(Accounts accounts) {
        if (!accounts.validate(Double.parseDouble(this.value))) {
            this.validation = false;
        }
        ;
    }

}
