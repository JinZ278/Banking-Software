package banking;

import static java.lang.Integer.parseInt;

public class TransferValidator {

    protected boolean validation;
    protected String accountId;
    protected String accountId2;
    protected String value;

    TransferValidator() {
        this.accountId = "";
        this.accountId2 = "";
        this.value = "0";
        this.validation = true;
    }

    public boolean transferValidate(String command, Bank bank) {
        String newString = stringIsSpaces(command);
        stringSplitter(newString);
        bothIdCheck(bank);
        if (this.validation == true) {
            valueCheck(bank);
        }
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

        if (firstWord.equals("transfer")) {
            stringAssignerTransfer(newStringSplitInArray);
        }
    }

    private void bothIdCheck(Bank bank) {
        idHasNoCharacters(this.accountId);
        idHasNoCharacters(this.accountId2);
        idWithinLimits(this.accountId);
        idWithinLimits(this.accountId2);
        idEightCharacters(this.accountId);
        idEightCharacters(this.accountId2);
        idExists(this.accountId, bank);
        idExists(this.accountId2, bank);
    }

    private void valueCheck(Bank bank) {
        if (!bank.getAccounts().get(this.accountId).validateWithdrawAmount(Double.parseDouble(this.value))) {
            this.validation = false;
        }
        if (!bank.getAccounts().get(this.accountId2).validateDepositAmount(Double.parseDouble(this.value))) {
            this.validation = false;
        }
    }

    public void stringAssignerTransfer(String[] arrOfStr) {
        if (arrOfStr.length == 4) {
            this.accountId = arrOfStr[1];
            this.accountId2 = arrOfStr[2];
            this.value = arrOfStr[3];
        } else {
            this.validation = false;
        }
    }

    private void idHasNoCharacters(String accountId) {
        try {
            parseInt(accountId);
        } catch (NumberFormatException e) {
            this.accountId = "0";
            this.accountId2 = "0";
            this.validation = false;
        }
    }

    public void idWithinLimits(String accountId) {
        if (parseInt(accountId) > 99999999) {
            this.validation = false;
        }
        if (parseInt(accountId) < 0) {
            this.validation = false;
        }
    }

    public void idEightCharacters(String accountId) {
        if (accountId.length() != 8) {
            this.validation = false;
        }
    }

    public void idExists(String accountId, Bank bank) {
        try {
            if (bank.getAccounts().get(accountId) == null) {
                this.validation = false;
            }
        } catch (Exception e) {
            this.validation = false;
        }
    }

}
