package banking;

import static java.lang.Integer.parseInt;

public class PassValidator {
    private String months;
    private boolean validation;

    PassValidator() {
        this.months = "";
        this.validation = true;
    }

    public boolean passValidate(String pass_command) {
        String newString = stringIsSpaces(pass_command);
        stringSplitter(newString);
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

        if (firstWord.equals("pass")) {
            stringAssignerPass(newStringSplitIntoArray);
        } else {
            this.validation = false;
        }
    }

    private void valueCheck() {
        valueIsNotString();
        valueWithinBounds();
    }

    private void stringAssignerPass(String[] newStringSplitIntoArray) {
        if (newStringSplitIntoArray.length == 2) {
            this.months = newStringSplitIntoArray[1];
        } else {
            this.validation = false;
        }
    }

    private void valueIsNotString() {
        try {
            parseInt(this.months);
        } catch (NumberFormatException e) {
            this.months = "0";
            this.validation = false;
        }
    }

    private void valueWithinBounds() {
        int month = parseInt(this.months);
        if (month < 1) {
            this.validation = false;
        }
        if (month > 60) {
            this.validation = false;
        }
    }
}
