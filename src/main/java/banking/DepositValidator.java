package banking;

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
        String[] newString = commandString.split(" ");
        if (newString.length == 0) {
            return false;
        }

        if (newString[0].toLowerCase().equals("deposit")) {
            stringAssignerDeposit(newString);
            idCheck(bank);
            depositValueCheck(bank);
            return this.validation;
        } else {
            return false;
        }
    }

    private void idCheck(Bank bank) {
        this.validation = bank.idCheck(this.accountId);
    }

    private void depositValueCheck(Bank bank) {
        Accounts account = bank.getAccounts().get(this.accountId);
        if (account != null && !account.validateDepositAmount(Double.parseDouble(this.value))) {
            this.validation = false;
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


}
