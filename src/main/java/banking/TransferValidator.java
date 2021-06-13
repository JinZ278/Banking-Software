package banking;

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
        String[] newString = command.split(" ");
        if (newString.length == 0) {
            return false;
        }

        if (newString[0].toLowerCase().equals("transfer")) {
            stringAssignerTransfer(newString);
            bothIdCheck(bank);
            valueCheck(bank);
            return this.validation;
        } else {
            return false;
        }

    }

    private void bothIdCheck(Bank bank) {
        if (!bank.idCheck(this.accountId) || !bank.idCheck(this.accountId2)) {
            this.validation = false;
        }
    }

    private void valueCheck(Bank bank) {
        if ((bank.getAccounts().get(this.accountId) != null) && (bank.getAccounts().get(this.accountId2) != null)) {
            if (bank.getAccounts().get(this.accountId).validateWithdrawAmount(Double.parseDouble(this.value)) == false) {
                this.validation = false;
            }
            if (bank.getAccounts().get(this.accountId2).validateDepositAmount(Double.parseDouble(this.value)) == false) {
                this.validation = false;
            }
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
}
