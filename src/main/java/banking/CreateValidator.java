package banking;

import static java.lang.Integer.parseInt;

class CreateValidator {

    protected String accountType;
    protected String accountId;
    protected String accountApr;
    protected String value;
    protected boolean validation;

    CreateValidator() {
        this.accountType = "";
        this.accountId = "";
        this.accountApr = "";
        this.value = "0";
        this.validation = true;
    }


    public boolean createValidate(String commandString, Bank bank) {
        String[] newString = commandString.split(" ");
        if (newString.length == 0) {
            this.validation = false;
            return this.validation;
        }

        if (newString[0].toLowerCase().equals("create")) {
            stringAssignerCreate(newString);
            idCheck(bank);
            aprCheck();
            valueCheck();
            return this.validation;
        } else {
            this.validation = false;
            return this.validation;
        }
    }

    private void idCheck(Bank bank) {
        int id = idHasNoCharacters();
        idAlreadyExistsInBank(bank);
        idEightCharacters();
        idValueCheck(id);
    }

    private void aprCheck() {
        double apr = aprStringCheck();
        aprValueCheck(apr);
    }

    private void valueCheck() {
        if (this.accountType.equals("cd") && Double.parseDouble(this.value) < 1000) {
            this.validation = false;
        }
        if (this.accountType.equals("cd") && Double.parseDouble(this.value) > 10000) {
            this.validation = false;
        }
    }

    public void stringAssignerCreate(String[] array) {
        String secondWord = array[1].toLowerCase();
        this.accountType = array[1].toLowerCase();
        if ((secondWord.equals("checking") || secondWord.equals("savings")) && array.length == 4) {
            this.accountId = array[2];
            this.accountApr = array[3];
        }
        if (secondWord.equals("cd") && array.length == 5) {
            this.accountId = array[2];
            this.accountApr = array[3];
            this.value = array[4];
        }
    }

    public int idHasNoCharacters() {
        try {
            return parseInt(this.accountId);
        } catch (NumberFormatException e) {
            this.accountId = "0";
            this.validation = false;
            return 0;
        }
    }

    public void idAlreadyExistsInBank(Bank bank) {
        if (bank.getAccounts().get(this.accountId) != null) {
            this.validation = false;
        }
    }

    public void idEightCharacters() {
        if (this.accountId.length() != 8) {
            this.validation = false;
        }
    }

    public void idValueCheck(int id) {
        if (id > 99999999) {
            this.validation = false;
        }
        if (id < 0) {
            this.validation = false;
        }
    }

    public double aprStringCheck() {
        try {
            return Double.parseDouble(this.accountApr);
        } catch (NumberFormatException e) {
            this.accountApr = "0";
            this.validation = false;
            return 0;
        }
    }

    public void aprValueCheck(double apr) {
        if (apr > 10) {
            this.validation = false;
        }
        if (apr < 0) {
            this.validation = false;
        }
    }

}