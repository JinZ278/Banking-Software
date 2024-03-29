package banking;

public class WithdrawValidator {

    protected String accountId;
    protected String value;
    protected boolean validation;

    WithdrawValidator() {
        this.accountId = "";
        this.value = "";
        this.validation = true;
    }

    public boolean withdrawValidate(String commandString, Bank bank) {
        String[] newString = commandString.split(" ");
        if (newString.length == 0) {
            return false;
        }

        if (newString[0].toLowerCase().equals("withdraw")) {
            stringAssignerWithdraw(newString);
            idCheck(bank);
            withdrawValueCheck(bank);
            return this.validation;
        } else {
            return false;
        }
    }

    private void idCheck(Bank bank) {
        this.validation = bank.idCheck(this.accountId);
    }

    private void withdrawValueCheck(Bank bank) {
        Accounts account = bank.getAccounts().get(this.accountId);
        if ((account != null) && (!account.validateWithdrawAmount(Double.parseDouble(this.value)))) {
            this.validation = false;
        }
    }

    private void stringAssignerWithdraw(String[] arrOfStr) {
        if (arrOfStr.length == 3) {
            this.accountId = arrOfStr[1];
            this.value = arrOfStr[2];
        } else {
            this.validation = false;
        }
    }
}
