public class CommandValidator {
    protected boolean validation;
    CreateValidator createValidator;
    DepositValidator depositValidator;

    CommandValidator() {
        this.validation = false;
    }

    public boolean validate(String command_string, Bank bank) {
        String[] stringInfoInArray = command_string.split(" ");
        if (command_string.isBlank()) {
            return this.validation;
        }
        if (stringInfoInArray[0].toLowerCase().equals("create")) {
            createValidator = new CreateValidator();
            this.validation = createValidator.validate(command_string, bank);
        }

        if (stringInfoInArray[0].toLowerCase().equals("deposit")) {
            depositValidator = new DepositValidator();
            this.validation = depositValidator.validate(command_string, bank);
        }

        return this.validation;
    }
}