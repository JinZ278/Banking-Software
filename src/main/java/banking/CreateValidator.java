package banking;

import static java.lang.Integer.parseInt;

class CreateValidator {

    protected String mainCommand;
    protected String accountType;
    protected String accountId;
    protected String accountApr;
    protected String value;
    protected boolean validation;

    CreateValidator() {
        this.mainCommand = "";
        this.accountType = "";
        this.accountId = "";
        this.accountApr = "";
        this.value = "0";
        this.validation = true;
    }


    public boolean validate(String commandString, Bank bank) {
        String newString = stringIsSpaces(commandString);
        stringSplitter(newString);
        idCheck(bank);
        aprCheck();
        valueCheck();
        return this.validation;
    }

    private String stringIsSpaces(String commandString) {
        if (commandString.isBlank()) {
            this.validation = false;
            return "";
        } else {
            return commandString;
        }
    }


    private void stringSplitter(String newString) {
        String[] newStringSplitIntoArray = newString.split(" ");
        String firstWord = newStringSplitIntoArray[0].toLowerCase();

        if (firstWord.equals("create")) {
            this.mainCommand = newStringSplitIntoArray[0].toLowerCase();
            stringAssignerCreate(newStringSplitIntoArray);
        }
    }

    private void idCheck(Bank bank) {
        idHasNoCharacters();
        idExistsInBank(bank);
        idEightCharacters();
        idValueCheck();
    }

    private void aprCheck() {
        aprStringCheck();
        aprValueCheck();
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
        if (secondWord.equals("checking") && array.length == 4) {
            this.accountId = array[2];
            this.accountApr = array[3];
        }
        if (secondWord.equals("savings") && array.length == 4) {
            this.accountId = array[2];
            this.accountApr = array[3];
        }
        if (secondWord.equals("cd") && array.length == 5) {
            this.accountId = array[2];
            this.accountApr = array[3];
            this.value = array[4];
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

    public void idExistsInBank(Bank bank) {
        if (this.mainCommand.equals("create") && bank.getAccounts().get(this.accountId) != null) {
            this.validation = false;
        }
    }

    public void idEightCharacters() {
        if (this.accountId.length() != 8) {
            this.validation = false;
        }
    }

    public void idValueCheck() {
        if (parseInt(this.accountId) > 99999999) {
            this.validation = false;
        }
        if (parseInt(this.accountId) < 0) {
            this.validation = false;
        }
    }

    public void aprStringCheck() {
        try {
            Double.parseDouble(this.accountApr);
        } catch (NumberFormatException e) {
            this.accountApr = "0";
            this.validation = false;
        }
    }

    public void aprValueCheck() {
        if (Double.parseDouble(this.accountApr) > 10) {
            this.validation = false;
        }
        if (Double.parseDouble(this.accountApr) < 0) {
            this.validation = false;
        }
    }

}