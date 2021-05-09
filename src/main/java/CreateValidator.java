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


    public boolean validate(String string, Bank bank) {
        String newString = stringIsSpaces(string);
        stringSplitter(newString);
        emptyInputs();
        idCheck(bank);
        aprCheck();
        valueCheck();
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


    private void stringSplitter(String string) {
        String[] arrOfStr = string.split(" ");
        if (arrOfStr[0].toLowerCase().equals("create")) {
            this.mainCommand = arrOfStr[0].toLowerCase();
            stringAssignerCreate(arrOfStr);
        }
    }

    private void emptyInputs() {
        if (this.mainCommand.equals("") || this.accountType.equals("") || this.accountId.equals("") || this.accountApr.equals("")) {
            this.validation = false;
        }
    }

    private void idCheck(Bank bank) {
        idHasNoCharacters();
        idExistsInBank(bank);
        idEightCharacters();
        idWithinLimits();
    }

    private void aprCheck() {
        aprNotString();
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
        String accountType = array[1].toLowerCase();
        this.accountType = array[1].toLowerCase();
        if (accountType.equals("checking") && array.length == 4) {
            this.accountId = array[2];
            this.accountApr = array[3];
        }
        if (accountType.equals("savings") && array.length == 4) {
            this.accountId = array[2];
            this.accountApr = array[3];
        }
        if (accountType.equals("cd") && array.length == 5) {
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

    public void idWithinLimits() {
        if (parseInt(this.accountId) > 99999999) {
            this.validation = false;
        }
        if (parseInt(this.accountId) < 0) {
            this.validation = false;
        }
    }

    public void aprNotString() {
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