package banking;

import static java.lang.Integer.parseInt;

public class DepositValidator {

    protected String accountId;
    protected String value;
    protected boolean validation;


    DepositValidator() {
        this.accountId = "";
        this.value = "";
        this.validation = true;
    }

    public boolean depositValidate(String commandString, Bank bank) {
        String newString = stringIsSpaces(commandString);
        stringSplitter(newString);
        idCheck(bank);
        valueCheck(bank);
        return this.validation;
    }

    private String stringIsSpaces(String string) {
        if (string.isBlank()) {
            this.validation = false;
            return "";
        } else {
            return string;
        }
    }

    private void stringSplitter(String newString) {
        String[] newStringSplitInArray = newString.split(" ");
        String firstWord = newStringSplitInArray[0].toLowerCase();

        if (firstWord.equals("deposit")) {
            stringAssignerDeposit(newStringSplitInArray);
        }
    }

    private void idCheck(Bank bank) {
        idHasNoCharacters();
        idWithinLimits();
        idEightCharacters();
        idExists(bank);
    }

    private void valueCheck(Bank bank) {
        Accounts account = bank.getAccounts().get(this.accountId);
        if (account != null) {
            if (!account.validateDepositAmount(Double.parseDouble(this.value))) {
                this.validation = false;
            }
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

    public void idEightCharacters() {
        if (this.accountId.length() != 8) {
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


}
