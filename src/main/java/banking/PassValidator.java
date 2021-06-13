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
        String[] newString = pass_command.split(" ");
        if (newString.length == 0) {
            return false;
        }

        if (newString[0].toLowerCase().equals("pass")) {
            stringAssignerPass(newString);
            valueCheck();
        } else {
            this.validation = false;
        }
        return this.validation;
    }

    private void valueCheck() {
        int months = valueIsNotString();
        valueWithinBounds(months);
    }

    private void stringAssignerPass(String[] newStringSplitIntoArray) {
        if (newStringSplitIntoArray.length == 2) {
            this.months = newStringSplitIntoArray[1];
        } else {
            this.validation = false;
        }
    }

    private int valueIsNotString() {
        try {
            return parseInt(this.months);
        } catch (NumberFormatException e) {
            this.months = "0";
            this.validation = false;
            return 0;
        }
    }

    private void valueWithinBounds(int month) {
        if (month < 1) {
            this.validation = false;
        }
        if (month > 60) {
            this.validation = false;
        }
    }
}
