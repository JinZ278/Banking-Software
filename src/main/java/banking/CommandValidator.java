package banking;

public class CommandValidator {
    protected Bank bank;
    protected boolean validation;
    CreateValidator createValidator;
    DepositValidator depositValidator;

    CommandValidator(Bank bank) {
        this.bank = bank;
        this.validation = false;
    }

    public boolean validate(String command_string) {
        String[] stringInfoInArray = command_string.split(" ");
        if (command_string.isBlank()) {
            return false;
        }

        if (stringInfoInArray[0].toLowerCase().equals("create")) {
            createValidator = new CreateValidator();
            this.validation = createValidator.validate(command_string, this.bank);
        }

        if (stringInfoInArray[0].toLowerCase().equals("deposit")) {
            depositValidator = new DepositValidator();
            this.validation = depositValidator.validate(command_string, this.bank);
        }

        return this.validation;
    }
}
