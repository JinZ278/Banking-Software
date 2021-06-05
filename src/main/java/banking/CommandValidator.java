package banking;

public class CommandValidator {
    protected Bank bank;
    protected boolean validation;

    CreateValidator createValidator;
    DepositValidator depositValidator;
    PassValidator passValidator;

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
            this.validation = createValidator.createValidate(command_string, this.bank);
        }

        if (stringInfoInArray[0].toLowerCase().equals("deposit")) {
            depositValidator = new DepositValidator();
            this.validation = depositValidator.depositValidate(command_string, this.bank);
        }

        if (stringInfoInArray[0].toLowerCase().equals("pass")) {
            passValidator = new PassValidator();
            this.validation = passValidator.passValidate(command_string);
        }

        return this.validation;
    }
}
